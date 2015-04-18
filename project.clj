(defproject lianliankan "0.1.0-SNAPSHOT"
            :description "FIXME: write description"
            :url "http://example.com/FIXME"
            :license {:name "Eclipse Public License"
                      :url  "http://www.eclipse.org/legal/epl-v10.html"}

            :source-paths ["src/clj" "src/cljs"]

            :dependencies [[org.clojure/clojure "1.6.0"]
                           [org.clojure/core.async "0.1.346.0-17112a-alpha"]

                           ;[cljsjs/react "0.12.2-5"]

                           [reagent-forms "0.4.4"]
                           [reagent-utils "0.1.3"]
                           [reagent "0.5.0" :exclusions [cljsjs/react]]
                           [cljsjs/react-with-addons "0.12.2-4"]
                           [secretary "1.2.2"]
                           [org.clojure/clojurescript "0.0-3058" :scope "provided"]
                           [cljs-ajax "0.3.10"]

                           [environ "1.0.0"]
                           [commons-io/commons-io "2.4"]
                           [http-kit "2.1.18"]
                           [ring "1.3.2"]
                           [ring/ring-defaults "0.1.4"]
                           [compojure "1.3.2"]
                           [ring-middleware-format "0.4.0"]
                           [metosin/ring-http-response "0.6.1"]
                           [metosin/compojure-api "0.18.0"]
                           [metosin/ring-swagger-ui "2.1.1-M1"]
                           [prone "0.8.0"]
                           [org.clojure/data.csv "0.1.2"]

                           [org.slf4j/slf4j-api "1.7.10"]
                           [ch.qos.logback/logback-classic "1.1.2"]

                           [cheshire "5.4.0"]
                           [org.clojure/data.codec "0.1.0"]
                           [clojurewerkz/scrypt "1.2.0"]
                           [crypto-random "1.2.0"]
                           [pandect "0.5.1"]
                           [com.taoensso/timbre "3.4.0"]
                           [com.postspectacular/rotor "0.1.0"]
                           [clj-time "0.9.0"]
                           [com.draines/postal "1.11.3"]
                           [im.chit/cronj "1.4.3"]
                           [cn.jpush.api/jpush-client "3.2.3"]

                           [cantata "0.1.17"]
                           [honeysql "0.5.1"]

                           [korma "0.4.0"]
                           ;[org.clojure/java.jdbc "0.3.5"]
                           [org.clojure/java.jdbc "0.3.6"]
                           ;[clojure.jdbc "0.4.0"]
                           ;[org.postgresql/postgresql "9.4-1201-jdbc41"]
                           [clj-postgresql "0.4.0"]
                           [org.xerial/sqlite-jdbc "3.7.2"]
                           ]

            :plugins [
                      [lein-cljsbuild "1.0.4"]
                      [lein-environ "1.0.0"]
                      [lein-ring "0.9.1"]
                      [lein-bin "0.3.4"]
                      [lein-asset-minifier "0.2.2"]]

            :ring {:handler      lianliankan.handler/app
                   :init         lianliankan.handler/init
                   :destroy      lianliankan.handler/destroy
                   :uberwar-name "lianliankan.war"}

            :min-lein-version "2.5.0"

            :uberjar-name "lianliankan.jar"

            :clean-targets ^{:protect false} ["resources/public/js"]

            :minify-assets {}

            :cljsbuild {:builds {:app {:source-paths ["src/cljs"]
                                       :compiler     {:output-to     "resources/public/js/app.js"
                                                      :output-dir    "resources/public/js/out"
                                                      ;;:externs       ["react/externs/react.js"]
                                                      :asset-path    "js/out"
                                                      :optimizations :none
                                                      :pretty-print  true}}}}

            :profiles {:dev        {:repl-options {:init-ns          lianliankan.handler
                                                   :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                                    :dependencies [[ring-mock "0.1.5"]
                                                   [ring/ring-devel "1.3.2"]
                                                   [leiningen "2.5.1"]
                                                   [figwheel "0.2.5-SNAPSHOT"]
                                                   [weasel "0.6.0-SNAPSHOT"]
                                                   [com.cemerick/piggieback "0.1.6-SNAPSHOT"]
                                                   [pjstadig/humane-test-output "0.6.0"]

                                                   ]

                                    :source-paths ["env/dev/clj"]
                                    :plugins      [[lein-figwheel "0.2.3-SNAPSHOT"]]

                                    :injections   [(require 'pjstadig.humane-test-output)
                                                   (pjstadig.humane-test-output/activate!)]

                                    :figwheel     {:http-server-root "public"
                                                   :server-port      3449
                                                   :css-dirs         ["resources/public/css"]
                                                   :ring-handler     lianliankan.handler/app}

                                    :env          {:dev? true}

                                    :cljsbuild    {:builds {:app {:source-paths ["env/dev/cljs"]
                                                                  :compiler     {:main       "lianliankan.dev"
                                                                                 :asset-path "js/out"
                                                                                 :source-map true}}
                                                            }
                                                   }}

                       :uberjar    {:hooks       [leiningen.cljsbuild minify-assets.plugin/hooks]
                                    :env         {:production true}
                                    :aot         :all
                                    :omit-source true
                                    :cljsbuild   {:jar    true
                                                  :builds {:app
                                                           {:source-paths ["env/prod/cljs"]
                                                            :compiler
                                                                          {:optimizations :advanced
                                                                           :pretty-print  false}}}}}

                       :production {:ring      {:open-browser? false
                                                :stacktraces?  false
                                                :auto-reload?  false}
                                    :cljsbuild {:builds {:app {:compiler {:main "lianliankan.prod"}}}}
                                    }})
