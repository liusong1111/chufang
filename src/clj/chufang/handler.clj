(ns chufang.handler
    (:require [compojure.core :refer [GET defroutes]]
              [compojure.route :as route :refer [not-found resources files]]
              [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
              [ring.middleware.format-params :refer [wrap-restful-params]]
              [clojure.java.io :as io]
              [prone.middleware :refer [wrap-exceptions]]
              [environ.core :refer [env]]
              [taoensso.timbre :as timbre]
              [com.postspectacular.rotor :as rotor]

              [pandect.algo.sha1 :refer [sha1]]
              [cheshire.core :as json]

              [ring.util.response :refer [file-response]]
              [ring.util.http-response :refer [ok]]
              [compojure.api.sweet :refer :all]
              [schema.core :as s]
              )
    (:import (java.io File)
             (org.apache.commons.io FileUtils)))

(defn init []
    (timbre/set-config! [:timestamp-pattern] "yyyy-MM-dd HH:mm:ss")
    (timbre/set-config!
        [:appenders :rotor]
        {:min-level             :info
         :enabled?              true
         :async?                false                       ; should be always false for rotor
         :max-message-per-msecs nil
         :fn                    rotor/append})
    (timbre/set-config!
        [:shared-appender-config :rotor]
        {:path "production.log" :max-size (* 1024 1024) :backlog 100})

    (timbre/info "chufang Server is starting"))

(defn destroy []
    (timbre/info "chufang Server is shutting down"))


(defapi app-routes
        ;(GET "/" [] (file-response "public/moments.html"))

        (resources "/")
        (files "/photos" {:root "./photos"})

        (defapi api
                {:formats [:json]}
                (swagger-ui)
                (swagger-docs)

                ;(swagger-ui "/api/ui"
                ;            :api-url "/docs"
                ;            )
                ;(swagger-docs "/docs"
                ;              :title "Api Docs"
                ;              :description "Api Docs")

                ;(GET "/" [] (file-response "public/moments.html" ))
                ;(resources "/" :root "")
                ;(not-found "Not Found")

                (swaggered "hello"
                           :description "it's a hello"
                           (GET* "/hi" []
                                 :query-params [q :- String]
                                 :summary "这是个HI，呼呼"
                                 (ok {:total (str "hello," q)})
                                 )
                           )
                (swaggered "api"
                           :description "API接口"

                           (context "/api" []
                                    (GET* "/api/hi" []
                                          (ok {:total "hi"}))
                                    )
                           (GET* "/api/hello" []
                                 ;(ok (m/users-index))
                                 (ok "hello")
                                 )

                           )

                (not-found "Not Found")
                )
        )


(def api-defaults
    "A default configuration for a HTTP API."
    {:params    {:urlencoded true
                 :multipart  true
                 :keywordize true}
     :responses {:not-modified-responses true
                 :absolute-redirects     true
                 :content-types          true}})

(def app
    (->
        app-routes
        (wrap-defaults api-defaults)
        wrap-restful-params
        )
    )

