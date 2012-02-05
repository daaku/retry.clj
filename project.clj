(defproject retry "1.0.3"
  :description "Retry on some exceptions."
  :author "Naitik Shah <n@daaku.org>"
  :url "https://github.com/nshah/retry.clj"
  :repl-init retry.repl
  :exclusions [org.clojure/clojure]
  :dependencies
    [[org.clojure/clojure "1.3.0"]
     [slingshot "0.10.1"]]
  :dev-dependencies
    [[auto-reload "1.0.3"]
     [lein-marginalia "0.7.0-SNAPSHOT"]
     [org.clojure/tools.logging "0.2.3"]
     [vimclojure/server "2.3.0-SNAPSHOT"]])
