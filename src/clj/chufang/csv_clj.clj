(ns chufang.csv-clj
    (:require [clojure.data.csv :as csv]
              [clojure.java.io :as io]
              [clojure.pprint :refer :all]
              )
    (:import (com.github.stuxuhai.jpinyin PinyinHelper PinyinFormat))
    )
(defn cn->en [filename]
    (PinyinHelper/convertToPinyinString filename "" PinyinFormat/WITHOUT_TONE)
    )

(defn fetch-data []
    (let [data (with-open [in-file (io/reader "doc/打靶.csv")]
                   (doall
                       (csv/read-csv in-file)))
          data (map #(map clojure.string/trim %) data)
          data (map (fn [[recipe source treat & slices]]
                        (let [slices (partition 3 3 nil slices)
                              slices (map (fn [[slice-name dosage option-slice]]
                                              {
                                               :slice-name            slice-name
                                               :slice-filename        (cn->en slice-name)
                                               :dosage                dosage
                                               :option-slice          option-slice
                                               :option-slice-filename (cn->en option-slice)
                                               }
                                              ) slices)
                              slices (remove #(clojure.string/blank? (:slice-name %)) slices)
                              slices (vec slices)
                              ]
                            {
                             :recipe          recipe
                             :recipe-filename (cn->en recipe)
                             :source          source
                             :treat           treat
                             :slices          slices
                             }
                            )
                        ) data)
          data (vec data)
          ]
        data
        ;(pprint data1)
        )
    )

(def data (fetch-data))

;; todo
;(defn copy-pics []
;    (doall (for [recipe data]
;               (try
;                   (io/copy (io/file (str "doc/连连看游戏/" name ".jpg")) (io/file (str "resources/public/pics/" filename ".jpg")))
;                   (catch Exception e
;                       (println "filename not found: " (str "doc/连连看游戏/" name ".jpg"))
;                       ))
;               ))
;
;    )

(defn -main []
    (pprint data)
    ;(copy-pics)
    )
