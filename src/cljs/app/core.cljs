(ns app.core)

(defn main []
  (let [c (.getElementById js/document "container")
        div (.. js/document (createElement "DIV"))]
    (set! (.-innerHTML c) (aset div "innerHTML" "<p>I'm dynamically created !!!</p>"))))
