(ns chufang.csv-clj
    (:require [clojure.data.csv :as csv]
              [clojure.java.io :as io]
              [clojure.pprint :refer :all]
              )
    (:import (com.github.stuxuhai.jpinyin PinyinHelper PinyinFormat)
             (org.apache.commons.io FileUtils FilenameUtils)
             (java.io File))
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
          data (vec (rest data))
          ]
        data

        ;(pprint data1)
        )
    )

(def data (fetch-data))

;; todo
(defn copy-pics []
    (doall (for [recipe data]
               (try
                   (FileUtils/forceMkdir (File. (str "resources/public/pics/" (:recipe-filename recipe))))
                   (doall (for [slice (:slices recipe)
                                :let [slices (if (clojure.string/blank? (:option-slice slice))
                                                 [slice]
                                                 [slice {:slice-name (:option-slice slice) :slice-filename (:option-slice-filename slice)}])]
                                s slices
                                ]
                              (let [source-filename (str "doc/处方/" (:recipe recipe) "/" (:slice-name s) ".jpg")
                                    source-file (io/file source-filename)
                                    exists? (.exists source-file)
                                    ]
                                  (if (not exists?)
                                      (println (str (:recipe recipe) "/" (:slice-name s) ".jpg"))
                                      (io/copy source-file (io/file (str "resources/public/pics/" (:recipe-filename recipe) "/" (:slice-filename s) ".jpg")))
                                      ;(println "File OK:" source-filename)
                                      )
                                  )
                              ))
                   (catch Exception e
                       (println e)
                       ))
               ))

    )

(defn -main []
    ;(pprint data)
    (copy-pics)
    )
