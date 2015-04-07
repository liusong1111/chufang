(ns lianliankan.widgets
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :refer-macros [defroute]]
              ))

;; fix
(defn index-of [coll v]
    (let [i (count (take-while #(not= v %) coll))]
        (when (or (< i (count coll))
                  (= v (last coll)))
            i)))

;store
(def items (atom []))
(def items-pair (atom []))
(def selected-item (atom nil))

; nil
(defn nil-item []
    {:name (rand) :treat "无" :form "无" :blank true}
    )

(defn demo-items []
    (vec (for [i (range 1 17)]
             {:name  (str "Name:" i)
              :treat (str "Treat:" i)
              :form  (str "Form:" i)
              }
             ))
    )

(defn demo-items-pair []
    (vec (for [i (range 1 17)]
             (str "Name:" i)
             ))
    )


(defn find-pair-name [item]
    (let [item-name (:name item)
          _ (println item-name)
          _ (println @items-pair)
          pos (index-of @items-pair item-name)
          _ (println "pos:" pos)
          ]
        (if (not= pos -1)
            (if (= (mod pos 2) 0)
                (get @items-pair (+ pos 1))
                (get @items-pair (- pos 1)))
            )
        )
    )
; 药品名,功能主治,性状
; 泡制药品名,功能主治,性状
; {:name "党参" :treat "味甘，性平，归脾、肺经，具有补中益气、健脾益肺的功效" :form "为类圆形或椭圆形厚片或小段。切面黄白色或淡黄棕色，有裂隙和菊花纹，中央有淡黄色圆心，周边淡黄白色或黄棕色，有纵皱纹。有特殊香气，味微甜"}
; 配对 ["党参", "米炒党参"], ["干姜", "炮姜"]
(defn on-item-click [item]
    (let [s @selected-item]
        (reset! selected-item item)
        (println "matching:" (:name item) "-" (:name s) "-" (:name (find-pair-name s)))
        (when (= (:name item) (find-pair-name s))
            (println "matched:" (:name item) "-" (:name s))
            (reset! items (replace {s (nil-item) item (nil-item)} @items))
            )
        )

    )

(defn item-view [item]
    [:img.item {
                :src      (str "images/" (:name item) ".jpg")
                :on-click #(on-item-click item)
                :class    (if (= item @selected-item) "selected")
                }]
    )


(defn matrix-view []
    [:div.items
     (for [item @items]
         ^{:key (:name item)} [item-view item]
         )
     ]
    )

(defn item-description-view []
    [:div
     (:name @selected-item)
     ]
    )


(defn new-game []
    (reset! selected-item nil)
    ;(reset! items [])
    (reset! items (demo-items))
    ;(reset! items-pair [])
    (reset! items-pair (demo-items-pair))
    )


(defn ^:export run []
    (let [ele (.getElementById js/document "matrix")]
        (reagent/render-component [matrix-view] ele)
        )

    (let [ele (.getElementById js/document "item-description")]
        (reagent/render-component [item-description-view] ele)
        )

    (new-game)
    )
