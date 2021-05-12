(set-env!
 :resource-paths #{"src" "html"}
 :dependencies '[[adzerk/boot-cljs            "2.1.5"      :scope "test"]
                 [adzerk/boot-cljs-repl       "0.4.0"          :scope "test"]
                 [adzerk/boot-reload          "0.6.1"          :scope "test"]
                 [pandeiro/boot-http          "0.7.1-SNAPSHOT" :scope "test"]
                 [crisptrutski/boot-cljs-test "0.2.2-SNAPSHOT" :scope "test"]
                 [org.clojure/clojure         "1.10.1"]
                 [org.clojure/clojurescript   "1.10.844"]
                 [cider/piggieback "0.3.9" :scope "test"]
                 [weasel "0.7.0" :scope "test"]
                 [nrepl "0.4.5" :scope "test"]])

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload    :refer [reload]]
  '[crisptrutski.boot-cljs-test  :refer [exit! test-cljs]]
  '[pandeiro.boot-http    :refer [serve]])

(deftask testing []
  (merge-env! :resource-paths #{"test"})
  identity)

(deftask auto-test []
  (comp (testing)
        (watch)
        (speak)
        (test-cljs)))

(deftask dev []
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload :on-jsload 'app.core/main)
        (cljs-repl)
        (cljs :source-map true :optimizations :none)))

(deftask test []
  (comp (testing)
        (test-cljs)
        (exit!)))

(deftask build []
  (cljs :optimizations :advanced))
