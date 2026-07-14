(ns ordinance.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [ordinance.facts :as facts]))

(deftest washington-dc-has-spec-basis
  (let [sb (facts/spec-basis "washington-dc")]
    (is (= 2 (count sb)))
    (is (every? #(str/starts-with? (:ordinance/url %) "https://code.dccouncil.gov/") sb))))

(deftest unknown-municipality-has-no-spec-basis
  (is (nil? (facts/spec-basis "chicago")))
  (is (nil? (facts/spec-basis "zzz"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["washington-dc" "chicago"])]
    (is (= 2 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["chicago"] (:missing-municipalities c)))))

(deftest by-topic-filters
  (is (= ["washington-dc.human-rights-act"]
         (mapv :ordinance/id (facts/by-topic "washington-dc" :anti-discrimination))))
  (is (empty? (facts/by-topic "washington-dc" :labor)))
  (is (empty? (facts/by-topic "chicago" :transparency))))
