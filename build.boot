(set-env!
 :source-paths #{"src/cljs"}
 :resource-paths #{"html"}
 :dependencies '[[org.clojure/clojure "1.8.0"]
                 [adzerk/boot-cljs "1.7.228-2"                 :scope "test"]
                 [adzerk/boot-cljs-repl "0.4.0"                :scope "test"]
                 [adzerk/boot-reload "0.6.0"                   :scope "test"]
                 [pandeiro/boot-http "0.7.5"                   :scope "test"]
                 [crisptrutski/boot-cljs-test "0.3.5-SNAPSHOT" :scope "test"]
                 [org.clojure/clojurescript "1.10.339"]
                 [cider/piggieback          "0.3.9"            :scope "test"]
                 [weasel                    "0.7.0"            :scope "test"]
                 [nrepl                     "0.4.5"            :scope "test"]])

(require '[adzerk.boot-cljs :refer [cljs]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
         '[adzerk.boot-reload :refer [reload]]
         '[crisptrutski.boot-cljs-test  :refer [test-cljs]]
         '[pandeiro.boot-http :refer [serve]]
         )

(task-options!
 cljs {:optimizations :none :source-map true}
 reload {:on-jsload 'app.core/main})

(deftask testing []
  (merge-env! :source-paths #{"test"})
  identity)

(deftask auto-test []
  (comp (testing)
        (watch)
        (speak)
        (test-cljs)))

(deftask dev []
  (comp (serve)
        (watch)
        (reload)
        (cljs-repl)
        (cljs)
        (target)))

(deftask test []
  (comp (testing)
        (test-cljs)))

(deftask build []
  (cljs :optimizations :advanced))
