
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.macros
             :refer
             [defcomp cursor-> <> div button textarea pre a span img style meta']]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]
            [app.style :as style]
            [respo-md.comp.md :refer [comp-md-block]]
            ["highlight.js" :as hljs]
            [app.schema :refer [dev?]]))

(defcomp
 comp-open-graph
 ()
 (div
  {}
  (meta' {:property "og:type", :content "website"})
  (meta' {:property "og:title", :content "shadow-cljs"})
  (meta' {:property "og:description", :content "ClojureScript compilation made easy!"})
  (meta' {:property "og:image", :content "http://cdn.tiye.me/logo/shadow-cljs.png"})
  (meta' {:property "og:url", :content "http://shadow-cljs.org"})))

(defn render-features []
  (div
   {:style {:margin "auto", :max-width 800, :padding 16, :padding-top 40}}
   (comp-md-block
    "shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use.\n\n* Good configuration defaults so you don't have to sweat the details\n* Supporting various targets :browser, :node-script, :npm-module, :react-native(exprimental)...\n* Live Reload (CLJS + CSS)\n* CLJS REPL\n* Importing CommonJS & ES6 modules from npm or local JavaScript files\n* Code splitting (via :modules)\n* Fast builds, reliable caching, ...\n\nTo get shadow-cljs, run:\n\n```bash\nnpm install -g shadow-cljs\n```\n\nMind that shadow-cljs needs JVM in order to compile ClojureScript.\n\n[Try with an demo project!](https://github.com/minimal-xyz/minimal-shadow-cljs-browser)\n\n### Configuration\n\nConfiguring a browser app with `shadow-cljs.edn`:\n\n```clojure\n{:source-paths [\"src\"]\n :dependencies [[reagent \"0.8.0-alpha2\"]]\n :builds {:app {:target :browser\n                :output-dir \"public/js\"\n                :asset-path \"/js\"\n                :modules {:main {:entries [my.app]}}}}}\n```\n\nSupposing the namespace for you main file is `my.app`:\n\n```bash\nshadow-cljs.edn\nsrc/\n  my/\n    app.cljs\npublic/\n  js/\n```\n\n`:target :browser` specifies it will build code for browsers. You may use `:target :node-script` [for Node.js code](https://github.com/minimal-xyz/minimal-shadow-cljs-nodejs). Find more about [targets](https://shadow-cljs.github.io/docs/UsersGuide.html#_build_target).\n\n### CLI tools\n\nCommonly used shadow-cljs commands during development:\n\n```bash\n# compile a build once and exit\nshadow-cljs compile app\n\n# compile and watch\nshadow-cljs watch app\n\n# connect to REPL for the build (available while watch is running)\nshadow-cljs cljs-repl app\n\n# connect to standalone node repl\nshadow-cljs node-repl\n```\n\nRunning a release build optimized for production use.\n\n```bash\nshadow-cljs release app\n```\n\n### Import npm modules\n\nWith shadow-cljs, most npm modules for browser can be imported with modules installed locally.\n\n```clojure\n(ns app.main\n  (:require [\"md5\" :as md5]\n            [\"fs\" :as fs]))\n\n(println (md5 \"text\"))\n\n(fs/readFileSync \"deps.den\" \"utf8\")\n```\n\nRead more at [Guide on how to use/import npm modules/packages in ClojureScript?](https://clojureverse.org/t/guide-on-how-to-use-import-npm-modules-packages-in-clojurescript/2298)\n\n### Hot code swapping\n\nshadow-cljs watch file changes and re-compiles in watching mode. Code are compiled incrementally, warnings and errors are displayed after prettified.\n\n```clojure\n{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n\n                    :devtools {:after-load app.main/reload!\n                               :http-root \"target\"\n                               :http-port 8080}}}}\n```\n\n### Long term caching\n\nBy setting in `:module-hash-names` field, you may tell shadow-cljs to add MD5 hash in the filenames generated. It's a trivial feature in Webpack, now it's one-liner config in ClojureScript. Meanwhile `assets.edn` file can be emitted for indexing js files in HTML.\n\n```clojure\n{:source-paths [\"src\"]\n :dependencies [[mvc-works/hsl \"0.1.2\"]]\n :builds {:browser {:target :browser\n                    :output-dir \"target/browser\"\n                    :modules {:main {:entries [app.main]}}\n                    \n                    :release {:output-dir \"dist/\"\n                              :module-hash-names 8\n                              :build-options {:manifest-name \"assets.edn\"}}}}}\n```\n\n### Other features\n\nThere are more features you may explore in shadow-cljs:\n\n* [Dynamic code loading](https://shadow-cljs.github.io/docs/UsersGuide.html#_loading_code_dynamically)\n* [Testing](https://shadow-cljs.github.io/docs/UsersGuide.html#_testing)\n* [Editor Integration](https://shadow-cljs.github.io/docs/UsersGuide.html#_editor_integration)\n\nRead [User Guide](https://shadow-cljs.github.io/docs/UsersGuide.html) for more features.\n"
    {:highlight (fn [code lang] (.-value (.highlight hljs lang code)))})))

(defn render-footer []
  (div
   {:style {:height 240,
            :background-color theme/dark,
            :margin-top 120,
            :color theme/green-dark,
            :padding 16}}
   (div
    {:style {:max-width 800, :margin :auto, :color :white}}
    (<> "This page is powered by shadow-cljs and Respo. ")
    (a
     {:href "https://github.com/shadow-cljs/shadow-cljs.org", :style {:color :white}}
     (<> "shadow-cljs/shadow-cljs.org")))))

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
     {:href "https://clojureverse.org/c/projects/shadow-cljs", :style {:color :white}}
     (<> "Forum")))))

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
    {:class-name "logo-circle",
     :style {:width 240, :height 240, :position :relative, :border-radius "50%"}}
    (div
     {:class-name "bubble circle-dark",
      :style {:border-radius "50%",
              :background-color "rgb(67,128,219)",
              :width 652,
              :height 652,
              :position :absolute}})
    (div
     {:class-name "bubble circle-green",
      :style {:border-radius "50%",
              :background-color "rgb(65,180,0)",
              :width 279,
              :height 279,
              :position :absolute}})
    (div
     {:class-name "bubble circle-blue",
      :style {:border-radius "50%",
              :background-color "#89b4ff",
              :width 71,
              :height 71,
              :position :absolute}})
    (div
     {:class-name "bubble circle-yellow",
      :style {:border-radius "50%",
              :background-color "#76e013",
              :width 129,
              :height 129,
              :position :absolute}})
    (div
     {:class-name "logo-s",
      :style {:background-image "url(http://cdn.tiye.me/logo/shadow-cljs-outline.png)",
              :background-size "cover",
              :position :relative,
              :width "100%",
              :height "100%",
              :pointer-events :none}}))
   (=< nil 32)
   (div
    {:style (merge ui/row-center {:flex-wrap :wrap})}
    (a
     {:href "https://medium.com/@jiyinyiyong/a-beginner-guide-to-compile-clojurescript-with-shadow-cljs-26369190b786",
      :target "_blank"}
     (button {:style style/button} (<> "Beginner Guide")))
    (=< 32 nil)
    (a
     {:href "https://shadow-cljs.github.io/docs/UsersGuide.html", :target "_blank"}
     (button {:style style/button} (<> "Manual")))
    (=< 32 nil)
    (a
     {:href "https://github.com/thheller/shadow-cljs/issues", :target "_blank"}
     (button {:style style/button} (<> "Feedback"))))))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style {:background-color :white}}
    (comp-open-graph)
    (render-header)
    (render-visual)
    (render-features)
    (render-footer)
    (if dev? (cursor-> :reel comp-reel states reel {})))))
