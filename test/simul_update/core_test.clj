(ns simul-update.core-test
  (:require [clojure.test :refer :all]
            [clojure.core.typed :as t]))

(deftest good-test
  (testing "type check simul-update.good"
    (is (t/check-ns 'simul-update.good))))

(deftest bad-test
  (testing "type check simul-update.bad"
    (is (thrown? Exception (t/check-ns 'simul-update.bad)))))
