(ns leiningen.new.jessitron
  (:require [leiningen.new.templates :refer [renderer sanitize year ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "jessitron"))

(defn jessitron
  "every project needs types and property tests!"
  [name]
  (let [data {:name name
              :sanitized (sanitize name)
              :year (year)}]
    (main/info "Generating fresh project with test.check and schema.")
    (->files data
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["project.clj" (render "project.clj" data)]
             ["README.md" (render "README.md" data)]
             ["LICENSE" (render "LICENSE" data)]
             [".gitignore" (render "gitignore" data)]
             ["test/{{sanitized}}/core_test.clj" (render "test.clj" data)]
             )))
