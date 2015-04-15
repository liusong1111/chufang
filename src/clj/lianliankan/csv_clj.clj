(ns lianliankan.csv-clj
    (:require [clojure.data.csv :as csv]
              [clojure.java.io :as io]
              [clojure.pprint :refer :all]
              )
    )
(defn -main []
    (let [data (with-open [in-file (io/reader "resources/public/data/data.csv")]
                   (doall
                       (csv/read-csv in-file)))]
        (pprint data)
        )
    )
