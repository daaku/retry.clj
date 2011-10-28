(ns retry.test.core
  "A library to retry on some exceptions"
  {:author "Naitik Shah"}
  (:use
    [retry.core :only [try-times]]
    [clojure.test :only [deftest testing is]]))

(defn throw-maker [count]
  (let [left (atom count)]
    (fn [] (if (= @left 0) :ok (do (swap! left dec) (throw (Exception.)))))))

(def throws0 (throw-maker 0))
(def throws1 (throw-maker 1))
(def throws2 (throw-maker 2))
(def throws3 (throw-maker 3))
(def throws4 (throw-maker 4))

(deftest throw-maker-throws
  (testing "Throws"
    (is (= :ok (throws0)) "not on 0")
    (is (thrown? Exception (throws1)) "on 1")))

(deftest retrying
  (testing "Retrying"
    (is (= :ok (try-times 1 Exception :ok)) "1 times works with bare value")
    (is (= :ok (try-times 3 Exception (throws2))) "3 times works with 2 throws")
    (is (= :ok (try-times 3 Exception (throws3))) "3 times works with 3 throws")
    (is (thrown? Exception (try-times 3 Exception (throws4)))
        "3 times fails with 4 throws")))
