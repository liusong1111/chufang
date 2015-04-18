(ns lianliankan.widgets
    (:require [reagent.core :as reagent :refer [atom]]
              [lianliankan.data :as data]
              ;[cljsjs.react :as React]
              ))

; for animation
;(def css-transition-group
;    (reagent/adapt-react-class js/React.addons.CSSTransitionGroup))

;(def animation-style "
;.foo-enter {
;  height: 0;
;  transition: height 0.27s ease-out;
;}
;
;.foo-leave {
;  height: 0;
;  transition: height 0.27s ease-out;
;}
;
;.foo-enter-active {
;  height: 2em;
;  opacity: 1;
;}")

; for layout
(def pair-capacity (atom 16))
(def item-size (atom 100))
(def description-style (atom {}))
(def content-style (atom {}))

; layout!
(defn layout! []
    (let [body (-> js/document .-body)
          w (.-clientWidth body)
          h (- (.-clientHeight body) 60)

          ;w (- w (* 3 12))
          img-size (quot (min w h) 4)
          -pair-capacity (* 2 (if (> w h) 4 (quot (- h 200) img-size)))
          ]
        (reset! item-size img-size)
        (if (> h w)
            (reset! description-style {
                                       :height   200
                                       :width    "100%"
                                       :bottom   0
                                       :position "fixed"
                                       })
            (do
                (reset! description-style {
                                           :height "100%"
                                           :width  (- w (* img-size 4))
                                           :top    44
                                           :left   (* img-size 4)

                                           })
                (reset! content-style {
                                       :padding-right (- w (* img-size 4))
                                       })
                )
            )
        (reset! pair-capacity -pair-capacity)
        )
    )


;store
(def items (atom []))
(def selected-item (atom nil))
(def timing (atom 0))
(def timing-ref (atom nil))

(defn ^:export new-game []
    (reset! items (data/sample-data @pair-capacity))
    (reset! selected-item nil)

    (reset! timing 0)
    (js/clearInterval @timing-ref)
    (reset! timing-ref (js/setInterval #(swap! timing inc) 1000))
    )

; nil
(defn nil-item []
    {:name (rand) :treat "无" :form "无" :blank true}
    )

(defn check-all-clear []
    (when (every? #(:blank %) @items)
        (js/clearInterval @timing-ref)
        )
    )

(defn on-item-click [item]
    (let [s @selected-item
          is-paired? (data/paired? (:name item) (:name s))
          ]
        (reset! selected-item item)
        ;(println "matching:" (:name item) "-" (:name s) "-" is-paired?)
        (when is-paired?
            ;(println "matched:" (:name item) "-" (:name s))
            (reset! items (replace {s (nil-item) item (nil-item)} @items))
            )
        (check-all-clear)
        )

    )

(defn item-view [item]
    [:img.item {
                :src      (if (:blank item) "blank.jpg" (str "pics/" (:name item) ".jpg"))
                :on-click #(on-item-click item)
                :class    (if (= item @selected-item) "selected")
                :style    {
                           :width  @item-size
                           :height @item-size
                           }
                }]
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
    (if (pos? @timing)
        [:a.btn.btn-link.pull-left "耗时："
         (timing-readable @timing)
         ]
        )
    )

(defn game-summary []
    [:div
     {:style {
              :text-align "center"
              }
      }
     [:h1
      {:style {
               :margin "20px auto"
               :color  "#00f"
               }
       }
      "恭喜通过！"]
     [:h2
      {:style {
               :margin    "20px auto"
               :color     "#666"
               :font-size 16
               }
       }
      "共耗时" (timing-readable @timing)]
     [:button.btn.btn-block.btn-negative
      {:on-click new-game}
      "再来一局"
      ]
     ]
    )

(defn matrix-view []
    [:div
     ;[:style animation-style]
     [:div.items
      {
       :style {
               :width (* @item-size 4)
               }
       }
      ;[css-transition-group {:transition-name "foo"}
       (for [item @items]
           ^{:key (:name item)} [item-view item]
           )
       ;]
      ]
     ]

    )


(defn page-main []
    [:div
     [:header.bar.bar-nav
      [:h1.title "药品连连看"]
      (if (pos? @timing)
          [timing-view]
          )
      [:a.btn.btn-nav.btn-negative.pull-right
       {
        :on-click new-game
        }
       "重开一局"]
      ]
     (if (and @selected-item (not (:blank @selected-item)))
         [:nav.bar.bar-tab.description
          {
           :style @description-style
           }
          [:span.field.name (:name @selected-item)]
          [:div
           [:span.field "功能主治："]
           [:span.treat (:treat @selected-item)]
           ]
          [:div
           [:span.field "性状："]
           [:span.form (:form @selected-item)]
           ]
          ]
         )

     [:div.content
      {
       :style @content-style
       }
      [:div#matrix
       (if (every? #(:blank %) @items)
           [game-summary]
           [matrix-view]
           )
       ]
      ]
     ]
    )

(defn ^:export run []
    (let [ele (.getElementById js/document "main")]
        (reagent/render-component [page-main] ele)
        )
    (layout!)
    (new-game)
    )
