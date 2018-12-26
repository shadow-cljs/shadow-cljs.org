
(ns build.main
  (:require [shadow.cljs.devtools.api :as shadow]
            [clojure.java.shell :refer [sh]]))

(defn sh! [command]
  (println command)
  (println (sh "bash" "-c" command)))

(defn watch []
  (shadow/watch :client))

(defn build-cdn []
  (sh! "rm -rf dist/*")
  (shadow/release :client)
  (shadow/compile :page)
  (shadow/compile :upload)
  (sh! "release=true cdn=true node target/page.js")
  (sh! "mkdir -p dist/entry/ && cp entry/shadow-cljs-demo.png dist/entry")
  (sh! "cp entry/manifest.json dist/"))

(defn build []
  (sh! "rm -rf dist/*")
  (shadow/release :client)
  (shadow/compile :page)
  (sh! "release=true node target/page.js")
  (sh! "mkdir -p dist/entry/ && cp entry/shadow-cljs-demo.png dist/entry")
  (sh! "cp entry/manifest.json dist/"))

(defn page []
  (shadow/compile :page)
  (sh! "node target/page.js")
  (sh! "cp entry/manifest.json target/"))
