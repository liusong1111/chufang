(ns chufang.widgets
    (:require [reagent.core :as reagent :refer [atom]]
              [chufang.data :as data]
              ))



; for layout
;(def item-size (atom 100))
;(def content-style (atom {}))

; layout!
;(defn layout! []
;    (let [body (-> js/document .-body)
;          w (.-clientWidth body)
;          h (- (.-clientHeight body) 60)
;
;          ;w (- w (* 3 12))
;          img-size (quot (min w h) 4)
;          -pair-capacity (* 2 (if (> w h) 4 (quot (- h 200) img-size)))
;          ]
;        (reset! item-size img-size)
;        (if (> h w)
;            (reset! description-style {
;                                       :height   200
;                                       :width    "100%"
;                                       :bottom   0
;                                       :position "fixed"
;                                       })
;            (do
;                (reset! description-style {
;                                           :height "100%"
;                                           :width  (- w (* img-size 4))
;                                           :top    44
;                                           :left   (* img-size 4)
;
;                                           })
;                (reset! content-style {
;                                       :padding-right (- w (* img-size 4))
;                                       })
;                )
;            )
;        (reset! pair-capacity -pair-capacity)
;        )
;    )


;store
(def recipes (atom []))
(def current-recipe (atom nil))
(def selected-slices (atom []))
(def optional-slices (atom []))

(def result (atom :undecided))

(def timing (atom 0))
(def timing-ref (atom nil))

(defn gen-optional-slices []
    (let [count-of-selected (count @selected-slices)
          slices (:slices @current-recipe)
          count-of-slices (count slices)
          ]
        (if (>= count-of-selected count-of-slices)
            []
            (let [slice (slices count-of-selected)
                  {:keys [slice-name slice-filename option-slice option-slice-filename]} slice
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
            )

        )
    )

(defn reset-game! []
    (reset! selected-slices [])
    (reset! optional-slices (gen-optional-slices))
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
    (if (nil? slice) "blank.jpg" (str "pics/" (:recipe-filename @current-recipe) "/" (:slice-filename slice) ".jpg"))
    )

(defn selected-slice-view [selected-slice slice]
    [:div.selected-slice
     {
      :style {
              :position "relative"
              }
      }
     [:img.slice
      {
       :src (slice-img-src selected-slice)
       }]
     (if (and (= @result :fail) (not (= (:slice-name selected-slice) (:slice-name slice))))
         [:div
          {
           :style {
                   :position "absolute"
                   }
           }
          "X"
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
     [:span.recipe-name (:recipe @current-recipe)]
     [:span.source (if (:source @current-recipe) (str "︽" (:source @current-recipe) "︾") "")]
     [:span.slices
      [:span.field-name "【组成】"]
      [:span
       (interpose
           "，"
           (for [slice (:slices @current-recipe)]
               [:span.slice
                [:span.slice-name (:slice-name slice)]
                [:span.dosage (:dosage slice)]
                ]
               ))
       ]
      ]
     [:span.treat
      [:span.field-name "【功用主治】"]
      [:span (:treat @current-recipe)]
      ]
     ]
    )

(defn selected-slices-view []
    [:div.selected-slices
     (for [select-index (range (count (:slices @current-recipe)))]
         (let [selected-slice (get @selected-slices select-index)
               slice (get (:slices @current-recipe) select-index)
               ]
             ;^{:key (:slice-name selected-slice)}
             [selected-slice-view selected-slice slice]
             )
         )
     ]
    )

(defn on-select-option [optional-slice]
    (swap! selected-slices conj optional-slice)
    (reset! optional-slices (gen-optional-slices))
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
     (for [optional-slice @optional-slices]
         [:img.optional-slice
          {
           :src      (slice-img-src optional-slice)
           :on-click #(on-select-option on-select-option)
           }
          ]
         )
     ]
    )
(defn result-view []
    [:div.result
     (case @result
         :undecided nil
         :success [:div
                   "共耗时" (timing-readable @timing)
                   [:span "正确"]
                   [:button.btn.btn-primary
                    {
                     :on-click new-game!
                     }
                    "下一关"]
                   ]
         :fail [:div
                [:span "错误"]
                [:button.btn.btn-negative
                 {
                  :on-click retry-game!
                  }
                 "重来"]
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
      [recipe-view]
      [selected-slices-view]
      [options-view]
      [result-view]
      ]
     ]
    )

(defn ^:export run []
    (let [ele (.getElementById js/document "main")]
        (reagent/render-component [page-main] ele)
        )
    ;(layout!)
    (new-game!)
    )
