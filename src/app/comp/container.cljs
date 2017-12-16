
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.macros :refer [defcomp cursor-> <> div button textarea pre a span]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]
            [app.style :as style]))

(defn render-header []
  (div
   {:style (merge
            ui/row-parted
            {:background-color theme/dark, :color :white, :height 60, :padding "0 16px"})}
   (div
    {:style ui/row-center}
    (div
     {:style {:background-image "url(http://cdn.tiye.me/logo/shadow-cljs-s.png)",
              :width 20,
              :height 40,
              :background-size :cover}})
    (=< 16 nil)
    (<> "shadow-cljs(Site under construction)"))
   (div
    {}
    (a
     {:href "https://github.com/thheller/shadow-cljs", :style {:color :white}}
     (<> "GitHub")))))

(defn render-visual []
  (div
   {:style (merge ui/center {:background-color theme/dark, :height 600, :color :white})}
   (div
    {:style {:background-image "url(http://cdn.tiye.me/logo/shadow-cljs.png)",
             :background-size "cover",
             :width 320,
             :height 320}})
   (=< nil 32)
   (div
    {:style {:font-size 20}}
    (<>
     "shadow-cljs provides everything you need to compile your ClojureScript code with a focus on simplicity and ease of use."))
   (=< nil 32)
   (div
    {:style ui/row-center}
    (button {:style style/button} (<> "Wiki"))
    (=< 32 nil)
    (button {:style style/button} (<> "Guide")))))

(defn render-snippet [code]
  (pre
   {:style {:margin 0,
            :padding 16,
            :background-color theme/dark,
            :color theme/green,
            :font-size 16,
            :font-family "Source Code Pro, Ubuntu Mono, Menlo, monospace"},
    :inner-text code}))

(defn render-feature [title] (div {:style {:font-size 24}} (<> title)))

(defn render-features []
  (div
   {:style {:margin "0 auto", :width 800}}
   (div
    {}
    (render-feature "Easy to install")
    (render-snippet
     "npm install -g shadow-cljs\n// or with yarn\nyarn global add shadow-cljs"))
   (div
    {}
    (render-feature "Easy to configure")
    (render-snippet
     "{:dependencies [[reagent \"0.8.0-alpha2\"]]\n\n :source-paths [\"src\"]\n\n :builds {:app {:target :browser\n                :output-dir \"public/js\"\n                :asset-path \"/js\"\n                :modules {:main {:entries [my.app]}}}}}\n"))))

(defn render-footer [] (div {:style {:height 400}}))

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
