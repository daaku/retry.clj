(ns retry.repl
  "repl helpers"
  {:author "Naitik Shah"}
  (:require
    [auto-reload.core]
    [clojure.tools.logging])
  (:use
    [retry.core :only [try-times]]))

(auto-reload.core/auto-reload ["src"])
