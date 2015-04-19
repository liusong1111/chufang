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
    (let [data (with-open [in-file (io/reader "doc/连连看游戏.csv")]
                   (doall
                       (csv/read-csv in-file)))
          data (map (fn [[name treat form]]
                        (->> [name (cn->en name) treat form]
                             (map clojure.string/trim)
                             vec
                             )
                        ) data)
          data (vec data)
          ]
        data
        ;(pprint data1)
        )
    )

(def data (fetch-data))

(defn copy-pics []
    (doall (for [[name filename treat form] data]
               (try
                   (io/copy (io/file (str "doc/连连看游戏/" name ".jpg")) (io/file (str "resources/public/pics/" filename ".jpg")))
                   (catch Exception e
                       (println "filename not found: " (str "doc/连连看游戏/" name ".jpg"))
                       ))
               ))

    )

(defn -main []
    ;(pprint data)
    (copy-pics)
    )
