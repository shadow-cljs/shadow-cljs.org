
(ns app.config )

(def cdn?
  (cond
    (exists? js/window) false
    (exists? js/process) (= "true" js/process.env.cdn)
    :else false))

(def dev?
  (let [debug? (do ^boolean js/goog.DEBUG)]
    (cond
      (exists? js/window) debug?
      (exists? js/process) (not= "true" js/process.env.release)
      :else true)))

(def site
  {:dev-ui "http://localhost:8100/main.css",
   :release-ui "https://cdn.tiye.me/favored-fonts/main.css",
   :cdn-url "https://cdn.tiye.me/shadow-cljs-org/",
   :title "shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use.",
   :icon "https://cdn.tiye.me/logo/shadow-cljs.png",
   :storage-key "shadow-cljs-org"})
