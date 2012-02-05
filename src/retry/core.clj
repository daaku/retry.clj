(ns retry.core
  "<a href='http://travis-ci.org/nshah/retry.clj'>
  <img src='https://secure.travis-ci.org/nshah/retry.clj.png'>
  </a>

  A library to retry on some exceptions."
  {:author "Naitik Shah"}
  (:use [slingshot.slingshot :only [throw+ try+]]))

(defmacro try-times
  "Try and retry the given body if the given exception matched."
  [retries exception-matcher & body]
  `(loop [retries# ~retries]
     (if-let [result# (try+
                        (do ~@body)
                        (catch ~exception-matcher e#
                          (when (zero? retries#) (throw+ e#))))]
       result#
       (recur (dec retries#)))))
