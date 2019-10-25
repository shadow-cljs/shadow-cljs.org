
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.core :as ui]
            [respo.core
             :refer
             [defcomp cursor-> <> div button textarea pre a span img style meta']]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]
            [app.style :as style]
            [respo-md.comp.md :refer [comp-md-block]]
            ["highlight.js" :as hljs]
            [app.config :refer [dev?]]
            [shadow.resource :refer [inline]]))

(defcomp comp-date (date) (<> date {:color (hsl 0 0 70), :font-family ui/font-fancy}))

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

(defcomp
 comp-post
 (date title link)
 (div {} (comp-date date) (=< 8 nil) (a {:href link, :inner-text title, :target "_blank"})))

(defn render-features []
  (div
   {:style {:margin "auto", :max-width 800, :padding 16, :padding-top 40}}
   (div
    {:style {:background-color (hsl 0 0 96), :padding 16}}
    (div {:style {:font-family ui/font-fancy, :font-size 20}} (<> "Recent posts"))
    (div
     {:style {:padding-top 12, :padding-left 12}}
     (comp-post
      "2019-10-24"
      "Introducing shadow-cljs Inspect"
      "https://clojureverse.org/t/introducing-shadow-cljs-inspect/5012")
     (comp-post
      "2019-08-25"
      "Hot Reload in ClojureScript"
      "https://code.thheller.com/blog/shadow-cljs/2019/08/25/hot-reload-in-clojurescript.html")
     (comp-post
      "2019-08-05"
      "“Upgrading” the react-native support"
      "https://clojureverse.org/t/upgrading-the-react-native-support/4669")))
   (comp-md-block
    (inline "introduction.md")
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
    (<> "Site built with shadow-cljs and ")
    (a
     {:href "http://respo-mvc.org",
      :inner-text "Respo",
      :target "_blank",
      :style {:color :white}})
    (<> ". Contribute content at ")
    (a
     {:href "https://github.com/shadow-cljs/shadow-cljs.org",
      :inner-text "shadow-cljs/shadow-cljs.org",
      :style {:color :white}})
    (<> "."))))

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
      :style {:background-image "url(https://cdn.tiye.me/logo/shadow-cljs-outline.png)",
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
