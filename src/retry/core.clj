(ns retry.core
  "A library to retry on some exceptions"
  {:author "Naitik Shah"}
  (:require [slingshot.core]))

(defmacro try-times [retries exception-matcher & body]
  `(loop [retries# ~retries]
     (if-let [result# (slingshot.core/try+
                         (do ~@body)
                         (catch ~exception-matcher e#
                           (when (zero? retries#) (slingshot.core/throw+ e#))))]
       result#
       (recur (dec retries#)))))
