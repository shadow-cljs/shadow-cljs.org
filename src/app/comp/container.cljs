
(ns app.comp.container
  (:require [hsl.core :refer [hsl]]
            [respo-ui.style :as ui]
            [respo.macros :refer [defcomp cursor-> <> div button textarea a span]]
            [verbosely.core :refer [verbosely!]]
            [respo.comp.space :refer [=<]]
            [reel.comp.reel :refer [comp-reel]]
            [app.theme :as theme]))

(defcomp
 comp-container
 (reel)
 (let [store (:store reel), states (:states store)]
   (div
    {:style {}}
    (div
     {:style (merge
              ui/row-parted
              {:background-color theme/dark, :color :white, :height 60, :padding "0 16px"})}
     (div {} (<> "shadow-cljs"))
     (div
      {}
      (a
       {:href "https://github.com/thheller/shadow-cljs", :style {:color :white}}
       (<> "GitHub"))))
    (div {:style {:background-color theme/dark, :height 600}})
    (cursor-> :reel comp-reel states reel {}))))
