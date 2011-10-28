(ns retry.core
  "A library to retry on some exceptions"
  {:author "Naitik Shah"}
  (:use
    [slingshot.core :only [try+]]))

(defmacro try-times [retries exception-matcher & body]
  `(loop [retries# ~retries]
     (if-let [result# (try+ (do ~@body)
                            (catch ~exception-matcher e#
                              (when (zero? retries#) (throw+ e#))))]
       result#
       (recur (dec retries#)))))
