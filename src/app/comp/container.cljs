
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros
             :refer
             [defcomp cursor-> <> div button textarea pre a span img style]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]
            [app.style :as style]
            [respo-md.comp.md :refer [comp-md-block]]
            ["highlight.js" :as hljs]))

(defn render-features []
  (div
   {:style {:margin "auto", :width 600, :max-width "100%"}}
   (style
    {:inner-text ".md-code-block {\n    background-color: rgb(12, 43, 82);\n    color: white;\n    font-family: \"Source Code Pro\", \"Ubuntu Mono\", Menlo, monospace;\n    font-size: 14px;\n    line-height: 24px;\n    margin: 0px;\n    max-width: 100%;\n    overflow: auto;\n    padding: 16px;\n    width: 600px;\n}"})
   (comp-md-block
    "### Easy to install\n\nshadow-cljs can be installed from npm, and being used as command-line tool. You may need JVM installed, but no need to configure JVM environment or Clojure environment before you can compile ClojureScript. shadow-cljs handles configurations for you.\n\n```bash\nnpm install -g shadow-cljs\n# or with yarn\nyarn global add shadow-cljs\n```\n\n### Easy to configure\n\nshadow-cljs uses a config file called shadow-cljs.edn . It's just a config file you can add things declaratively, instead of writing scripts to wind up plugins.\n\n```clojure\n{:dependencies [[reagent \"0.8.0-alpha2\"]]\n\n :source-paths [\"src\"]\n\n :builds {:app {:target :browser\n                :output-dir \"public/js\"\n                :asset-path \"/js\"\n                :modules {:main {:entries [my.app]}}}}}\n```\n\n### Import JavaScript code from npm\n\nIn shadow-cljs, importing npm package is becoming trivial.\n\n```clojure\n(ns app.main\n  (:require [\"md5\" :as md5]\n            [\"fs\" :as fs]))\n\n(println (md5 \"text\"))\n\n(fs/readFileSync \"deps.den\" \"utf8\")\n```\n\n### Hot code swapping\n\nRoughly same hot code swapping support like in lein-figwheel. Code are compiled incrementally, error messages are prettified.\n\n```clojure\n{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n\n                    :devtools {:after-load app.main/reload!\n                               :preloads [shadow.cljs.devtools.client.hud]\n                               :http-root \"target\"\n                               :http-port 8080}}}}\n```\n\n### Long term caching\n\nBy setting in :module-hash-names field, you may tell shadow-cljs to add MD5 hash in the filenames generated. It's a trivial feature in Webpack, now it's one-liner config in ClojureScript. Meanwhile manifest.edn file can be emitted for indexing js files in HTML.\n\n```clojure\n{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n                    \n                    :release {:output-dir \"dist/\"\n                              :module-hash-names 8\n                              :build-options {:manifest-name \"assets.edn\"}}}}}\n```\n\n### Dynamic code loading\n\nshadow-cljs supports simple dynamic import provided by Google Closure Compiler. So you may split code into seperated bundles and load when needed.\n\n```clojure\n(ns app.extra)\n\n(defn async-code! []\n  (println \"async code!\"))\n\n; another file\n\n(ns app.main\n  (:require [shadow.loader :as loader]))\n\n(defn on-load []\n  ((resolve 'app.extra/async-code!))\n\n(defn main! []\n  (loader/with-module \"extra\" on-load))\n```\n"
    {:highlight (fn [code lang] (.-value (.highlight hljs lang code)))})))

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
    (a {:href "https://code.thheller.com/", :style {:color :white}} (<> "Blogs"))
    (=< 32 nil)
    (a
     {:href "https://github.com/thheller/shadow-cljs", :style {:color :white}}
     (<> "GitHub"))
    (=< 32 nil)
    (a
     {:href "https://clojurians.slack.com/messages/C6N245JGG/", :style {:color :white}}
     (<> "Slack"))
    (=< 32 nil)
    (a
     {:href "https://github.com/thheller/shadow-cljs/releases", :style {:color :white}}
     (<> "Releases")))))

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
    (<> "ClojureScript compilation made easy!")
    (=< 8 nil)
    (img
     {:style {:vertical-align :middle}, :src "https://img.shields.io/npm/v/shadow-cljs.svg"}))
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
     {:href "https://clojureverse.org/c/projects/shadow-cljs", :target "_blank"}
     (button {:style style/button} (<> "Forum"))))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style {:background-color :white}}
    (render-header)
    (render-visual)
    (render-features)
    (render-footer)
    (cursor-> :reel comp-reel states reel {}))))
