(ns app.core)

(defn main []
  (let [c (.. js/document (createElement "DIV"))]
    (str
     (clojure.string/replace nil "" "")
     (clojure.string/replace "foo" "f" "b")
     (clojure.string/replace "bar" "b" "f"))

    (aset c "innerHTML" "<p>i'm dynamically created</p>")
    (.. js/document (getElementById "container") (appendChild c))))

