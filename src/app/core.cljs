(ns app.core
  (:require [clojure.string :as s]))

(defn main []
  (let [c (.. js/document (createElement "DIV"))]
    (str
     (s/replace nil "" "")
     (s/replace "foo" "f" "b")
     (s/replace "bar" "b" "f"))

    (aset c "innerHTML" "<p>i'm dynamically created</p>")
    (.. js/document (getElementById "container") (appendChild c))))

