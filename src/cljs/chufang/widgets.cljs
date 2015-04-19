(ns chufang.widgets
    (:require [reagent.core :as reagent :refer [atom]]
              [chufang.data :as data]
              ))



; for layout
(def recipe-size (atom 100))
(def img-size (atom 100))

; layout!
(defn layout! []
    (let [body (-> js/document .-body)
          w (.-clientWidth body)
          h (- (.-clientHeight body) 60)

          -recipe-size (quot w 2)
          -img-size (- (quot w 4) 10)
          ]
        (reset! img-size -img-size)
        (reset! recipe-size -recipe-size)
        )
    )


;store
(def recipes (atom []))
(def current-recipe (atom nil))
(def selected-slices (atom []))
(def optional-slices (atom []))
(def current-slice (atom nil))

(def result (atom :undecided))

(def timing (atom 0))
(def timing-ref (atom nil))

(defn gen-optional-slices! []
    (let [count-of-selected (count @selected-slices)
          slices (:slices @current-recipe)
          count-of-slices (count slices)
          slice (get slices count-of-selected)
          options (let [{:keys [slice-name slice-filename option-slice option-slice-filename]} slice
                        options [{
                                  :slice-name     slice-name
                                  :slice-filename slice-filename
                                  }
                                 {
                                  :slice-name     option-slice
                                  :slice-filename option-slice-filename
                                  }
                                 ]
                        options (remove #(clojure.string/blank? (:slice-name %)) options)
                        options (shuffle options)
                        ]
                      options
                      )
          ]
        (reset! optional-slices options)
        (reset! current-slice slice)
        )
    )

(defn reset-game! []
    (reset! selected-slices [])
    (gen-optional-slices!)
    (reset! result :undecided)

    (reset! timing 0)
    (js/clearInterval @timing-ref)
    (reset! timing-ref (js/setInterval #(swap! timing inc) 1000))
    )

(defn new-game! []
    (if (empty? @recipes)
        (reset! recipes (data/sample-data))
        )

    (reset! current-recipe (first @recipes))
    (reset! recipes (rest @recipes))

    (reset-game!)
    )

(defn retry-game! []
    (reset-game!)
    )

(defn slice-img-src [slice]
    ;(if (nil? slice) "blank.jpg" (str "pics/" (:recipe-filename @current-recipe) "/" (:slice-filename slice) ".jpg"))
    (if (nil? slice) "blank.jpg" (str "pics/" (:recipe @current-recipe) "/" (:slice-name slice) ".jpg"))
    )

(defn selected-slice-view [selected-slice slice]
    [:div.selected-slice
     {
      :style {
              :position     "relative"
              :display      "inline-block"
              :width        @img-size
              :height       @img-size
              :margin-right 4
              }
      }
     [:img.slice
      {
       :src   (slice-img-src selected-slice)
       :style {
               :width  @img-size
               :height @img-size
               }
       }]
     (if (and (= @result :fail) (not (= (:slice-name selected-slice) (:slice-name slice))))

         [:div
          {
           :style {
                   :position    "absolute"
                   :left        "50%"
                   :top         "50%"
                   :z-index     1
                   :margin-left -25
                   :margin-top  -25
                   :opacity     0.6
                   }
           }
          [:i.icon.fa.fa-times {
                                :style {
                                        :color     "#f00"
                                        :font-size 50
                                        }
                                }]
          ]

         )
     ]
    )

(defn timing-readable [timing]
    (let [hours (quot timing 3600)
          minutes (quot (- timing (* hours 3600)) 60)
          seconds (- timing (* hours 3600) (* minutes 60))
          ]
        (clojure.string/join "" (cond-> []
                                        (pos? hours) (conj (str hours "小时"))
                                        (pos? minutes) (conj (str minutes "分"))
                                        (pos? seconds) (conj (str seconds "秒"))
                                        ))
        )
    )

(defn timing-view []
    [:a.btn.btn-link.pull-left "耗时："
     (timing-readable @timing)
     ]
    )

(defn recipe-view []
    [:div.recipe
     {
      :style {
              :display        "tabel-cell"
              :vertical-align "top"
              :width          @recipe-size
              :height         (+ (* @img-size 3) 20)
              }
      }
     [:span.recipe-name (:recipe @current-recipe)]
     [:span.source (if (:source @current-recipe) (str "︽" (:source @current-recipe) "︾") "")]
     [:span.slices
      [:span.field-name "︻组成︼"]
      [:span
       (interpose
           "，"
           (for [slice (:slices @current-recipe)]
               ^{:key (:slice-name slice)}
               [:span.slice
                [:span.slice-name (:slice-name slice)]
                [:span.dosage (:dosage slice)]
                ]
               ))
       ]
      ]
     [:span.treat
      [:span.field-name "︻功用主治︼"]
      [:span (:treat @current-recipe)]
      ]
     ]
    )

(defn selected-slices-view []
    [:div.selected-slices
     {
      :style {
              :display        "table-cell"
              :vertical-align "top"
              :width          @recipe-size
              :height         @recipe-size
              :padding-top    6
              }
      }
     (doall (for [select-index (range (count (:slices @current-recipe)))]
                (let [selected-slice (get @selected-slices select-index)
                      slice (get (:slices @current-recipe) select-index)
                      ]
                    ^{:key (:slice-name (or selected-slice slice))}
                    [selected-slice-view selected-slice slice]
                    )
                ))

     ]
    )

(defn on-select-option [optional-slice]
    (swap! selected-slices conj optional-slice)
    (gen-optional-slices!)
    (let [selected-count (count @selected-slices)
          slice-count (count (:slices @current-recipe))
          ]
        (when (>= selected-count slice-count)
            (js/clearInterval @timing-ref)
            (let [selected-slice-names (map :slice-name @selected-slices)
                  slice-names (map :slice-name (:slices @current-recipe))
                  ok (= selected-slice-names slice-names)
                  r (if ok :success :fail)
                  ]
                (reset! result r)
                )
            )
        )
    )

(defn options-view []
    [:div.options
     {
      :style {
              :display        "table-cell"
              :vertical-align "top"
              }
      }
     (if (not (empty? @optional-slices))
         [:div
          {
           :style {
                   :font-size 14
                   }
           }
          "请从中选择："
          [:span
           {
            :style {
                    :font-size   16
                    :font-weight "bold"
                    :color       "#00f"
                    }
            }
           (:slice-name @current-slice)]
          ]
         )
     (doall (for [optional-slice @optional-slices]
                ^{:key (:slice-name optional-slice)}
                [:img.optional-slice
                 {
                  :src      (slice-img-src optional-slice)
                  :on-click #(on-select-option optional-slice)
                  :style    {
                             :width        @img-size
                             :height       @img-size
                             :margin-right 4
                             }
                  }
                 ]
                ))
     ]
    )
(defn result-view []
    [:div.result
     {
      :style {
              :display        "table-cell"
              :vertical-align "top"
              :padding-top    20
              :text-align     "center"
              }
      }
     (case @result
         :undecided nil
         :success [:div
                   [:span
                    {
                     :style {
                             :color "#333"
                             }
                     }
                    "共耗时" (timing-readable @timing)]
                   [:br]
                   [:button.btn.btn-block.btn-primary
                    {
                     :on-click new-game!
                     }
                    [:i.fa.fa-thumbs-up]
                    " 正确，下一关"]
                   ]
         :fail [:div
                [:span
                 {
                  :style {
                          :color "#333"
                          }
                  }
                 "共耗时" (timing-readable @timing)]
                [:br]
                [:button.btn.btn-block.btn-negative
                 {
                  :on-click retry-game!
                  }
                 [:i.fa.fa-thumbs-down]
                 " 错误，再来一次"]
                ]
         )
     ]
    )


(defn page-main []
    [:div
     [:header.bar.bar-nav
      [:h1.title "处方应付"]
      [timing-view]
      [:a.btn.btn-nav.btn-negative.pull-right
       {
        :on-click new-game!
        }
       "重开一局"]
      ]
     [:div.content
      [:div {
             :style {
                     :display "table-row"
                     }
             }
       [recipe-view]
       [selected-slices-view]
       ]

      [:div {
             :style {
                     :display "table-row"
                     }
             }
       [options-view]
       [result-view]
       ]

      ]
     ]
    )

(defn ^:export run []
    (let [ele (.getElementById js/document "main")]
        (reagent/render-component [page-main] ele)
        )
    (layout!)
    (new-game!)
    )
