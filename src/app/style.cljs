
(ns app.style
  (:require [respo-ui.style :as ui] [hsl.core :refer [hsl]] [app.theme :as theme]))

(def button
  (merge
   ui/button
   {:height 48,
    :line-height "48px",
    :border-radius "24px",
    :font-size 20,
    :font-family "Josefin Sans, Helvetica, Arial, sans-serif",
    :padding "0 16px",
    :background-color theme/green}))
