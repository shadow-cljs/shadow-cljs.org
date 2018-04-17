
(ns app.style
  (:require [respo-ui.core :as ui] [hsl.core :refer [hsl]] [app.theme :as theme]))

(def button
  (merge
   ui/button
   {:height 48,
    :line-height "48px",
    :border-radius "24px",
    :font-size 20,
    :font-family ui/font-fancy,
    :padding "0 32px",
    :background-color :transparent,
    :border (str "1px solid " "white"),
    :color :white}))
