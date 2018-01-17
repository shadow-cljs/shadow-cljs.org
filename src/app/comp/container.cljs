
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.macros :refer [defcomp cursor-> <> div button textarea pre a span]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]
            [app.style :as style]
            ["highlight.js" :as hljs]))

(defn render-description [description]
  (div {:style {:font-size 16, :padding "8px 16px"}} (<> description)))

(defn render-feature [title]
  (div
   {:style {:font-size 24, :line-height "40px", :margin-top 40, :padding "0 0px"}}
   (<> title)))

(defn render-snippet [lang code]
  (pre
   {:style {:margin 0,
            :padding 16,
            :background-color theme/dark,
            :color "white",
            :font-size 14,
            :font-family "Source Code Pro, Ubuntu Mono, Menlo, monospace",
            :line-height "24px",
            :width 600,
            :max-width "100%",
            :overflow :auto},
    :innerHTML (.-value (.highlight hljs lang code))}))

(defn render-features []
  (div
   {:style {:margin "auto", :width 600, :max-width "100%"}}
   (div
    {}
    (render-feature "Easy to install")
    (render-description
     "shadow-cljs can be installed from npm, and being used as command-line tool. You may need JVM installed, but no need to configure JVM environment or Clojure environment before you can compile ClojureScript. shadow-cljs handles configurations for you.")
    (render-snippet
     "bash"
     "npm install -g shadow-cljs\n# or with yarn\nyarn global add shadow-cljs"))
   (div
    {}
    (render-feature "Easy to configure")
    (render-description
     "shadow-cljs uses a config file called shadow-cljs.edn . It's just a config file you can add things declaratively, instead of writing scripts to wind up plugins.")
    (render-snippet
     "clojure"
     "{:dependencies [[reagent \"0.8.0-alpha2\"]]\n\n :source-paths [\"src\"]\n\n :builds {:app {:target :browser\n                :output-dir \"public/js\"\n                :asset-path \"/js\"\n                :modules {:main {:entries [my.app]}}}}}\n"))
   (div
    {}
    (render-feature "Import JavaScript code from npm")
    (render-description "In shadow-cljs, importing npm package is becoming trivial.")
    (render-snippet
     "clojure"
     "(ns app.main\n  (:require [\"md5\" :as md5]\n            [\"fs\" :as fs]))\n\n(println (md5 \"text\"))\n\n(fs/readFileSync \"deps.den\" \"utf8\")\n"))
   (div
    {}
    (render-feature "Hot code swapping")
    (render-description
     "Roughly same hot code swapping support like in lein-figwheel. Code are compiled incrementally, error messages are prettified.")
    (render-snippet
     "clojure"
     "{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n\n                    :devtools {:after-load app.main/reload!\n                               :preloads [shadow.cljs.devtools.client.hud]\n                               :http-root \"target\"\n                               :http-port 8080}}}}\n"))
   (div
    {}
    (render-feature "Long term caching")
    (render-description
     "By setting in :module-hash-names field, you may tell shadow-cljs to add MD5 hash in the filenames generated. It's a trivial feature in Webpack, now it's one-liner config in ClojureScript. Meanwhile manifest.edn file can be emitted for indexing js files in HTML.")
    (render-snippet
     "clojure"
     "{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n                    \n                    :release {:output-dir \"dist/\"\n                              :module-hash-names 8\n                              :build-options {:manifest-name \"assets.edn\"}}}}}\n"))
   (div
    {}
    (render-feature "Dynamic code loading")
    (render-description
     "shadow-cljs supports simple dynamic import provided by Google Closure Compiler. So you may split code into seperated bundles and load when needed.")
    (render-snippet
     "clojure"
     "(ns app.extra)\n\n(defn async-code! []\n  (println \"async code!\"))\n\n; another file\n\n(ns app.main\n  (:require [shadow.loader :as loader]\n            [app.extra :as extra]))\n\n(defn on-load []\n  (extra/async-code!))\n\n(defn main! []\n  (loader/with-module \"extra\" on-load))\n"))))

(defn render-footer []
  (div
   {:style {:height 120,
            :background-color theme/dark,
            :margin-top 120,
            :color theme/green-dark,
            :padding 16}}
   (<> "This page is powered by shadow-cljs and Respo. ")
   (a
    {:href "https://github.com/shadow-cljs/shadow-cljs.org",
     :style {:color theme/green-dark}}
    (<> "shadow-cljs/shadow-cljs.org"))))

(defn render-header []
  (div
   {:style (merge
            ui/row-parted
            {:background-color theme/dark, :color :white, :height 60, :padding "0 16px"})}
   (div {:style ui/row-center})
   (div
    {}
    (a
     {:href "https://github.com/thheller/shadow-cljs/wiki/ClojureScript-for-the-browser",
      :style {:color :white}}
     (<> "Wiki"))
    (=< 32 nil)
    (a {:href "https://code.thheller.com/", :style {:color :white}} (<> "Blogs"))
    (=< 32 nil)
    (a
     {:href "https://github.com/thheller/shadow-cljs", :style {:color :white}}
     (<> "GitHub")))))

(defn render-visual []
  (div
   {:style (merge ui/center {:background-color theme/dark, :height 600, :color :white})}
   (div
    {:style {:font-size 64,
             :padding "0 16px",
             :font-family "Josefin Sans, Helvetica, sans-serif",
             :font-weight 100}}
    (<> "shadow-cljs"))
   (div
    {:style {:font-size 18,
             :padding "0 16px",
             :font-family "Hind, Helvetica, sans-serif",
             :font-weight 100}}
    (<> "ClojureScript compilation made easy!"))
   (=< nil 32)
   (div
    {:style {:background-image "url(http://cdn.tiye.me/logo/shadow-cljs.png)",
             :background-size "cover",
             :width 240,
             :height 240}})
   (=< nil 32)
   (div
    {:style ui/row-center}
    (a
     {:href "https://medium.com/@jiyinyiyong/a-beginner-guide-to-compile-clojurescript-with-shadow-cljs-26369190b786",
      :target "_blank"}
     (button {:style style/button} (<> "Beginner")))
    (=< 32 nil)
    (a
     {:href "https://shadow-cljs.github.io/docs/UsersGuide.html", :target "_blank"}
     (button {:style style/button} (<> "User Guide")))
    (=< 32 nil)
    (a
     {:href "https://clojurians.slack.com/messages/C6N245JGG/", :target "_blank"}
     (button {:style style/button} (<> "Join Slack"))))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style {}}
    (render-header)
    (render-visual)
    (render-features)
    (render-footer)
    (cursor-> :reel comp-reel states reel {}))))
