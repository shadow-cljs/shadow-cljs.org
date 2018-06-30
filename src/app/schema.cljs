
(ns app.schema )

(def dev?
  (if (exists? js/window) (do ^boolean js/goog.DEBUG) (= "dev" (-> js/process .-env .-env))))

(def store {:states {}, :content ""})
