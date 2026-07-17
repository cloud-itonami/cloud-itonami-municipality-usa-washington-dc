(ns culture.facts-test
  (:require [clojure.edn :as edn]
            [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [culture.facts :as facts]))

(deftest washington-dc-has-culture-basis
  (let [sb (facts/spec-basis "washington-dc")]
    (is (= 9 (count sb)))
    (is (= (count sb) (count (set (map :culture/id sb)))))
    (is (every? #(str/starts-with? (:culture/url %) "https://") sb))
    (is (every? #(= "washington-dc" (:culture/municipality %)) sb))
    (is (every? #(= "USA" (:culture/country %)) sb))
    (is (every? #(seq (:culture/summary %)) sb))
    (is (every? #(string? (:culture/retrieved-at %)) sb))))

(deftest unknown-municipality-has-no-basis
  (is (nil? (facts/spec-basis "chicago")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["washington-dc" "chicago"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["chicago"] (:missing-municipalities c)))))

(deftest by-kind-filters
  (is (= 3 (count (facts/by-kind "washington-dc" :dish))))
  (is (= ["washington-dc.beverage.rickey"]
         (mapv :culture/id (facts/by-kind "washington-dc" :beverage))))
  (is (empty? (facts/by-kind "washington-dc" :craft)))
  (is (empty? (facts/by-kind "chicago" :dish))))

(deftest tx-file-matches-catalog
  (let [tx (edn/read-string (slurp "data/culture-tx.edn"))
        flat (mapcat val (sort-by key facts/catalog))]
    (is (= (vec flat) (vec tx)))))
