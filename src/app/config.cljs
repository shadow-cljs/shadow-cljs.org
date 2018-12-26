
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
   :release-ui "http://cdn.tiye.me/favored-fonts/main.css",
   :cdn-url "http://cdn.tiye.me/shadow-cljs-org/",
   :cdn-folder "tiye.me:cdn/shadow-cljs-org",
   :title "shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use.",
   :icon "http://cdn.tiye.me/logo/shadow-cljs.png",
   :storage-key "shadow-cljs-org",
   :upload-folder "tiye.me:repo/shadow-cljs/shadow-cljs.org/"})
