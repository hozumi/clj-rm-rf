(ns hozumi.test-rm-rf
  (:use [hozumi.rm-rf] :reload)
  (:use [clojure.test])
  (:require [clojure.java.io :as io :only [file]]))

(defn mk-temp-file
  [f]
  (doto f
    (.createNewFile)
    (.deleteOnExit)))

(defn mk-temp-dir
  [f]
  (doto f
    (.mkdir)
    (.deleteOnExit)))

(defn mk-tmpfs
  "Takes a tree of vectors and make directories and files under parent."
  [parent node]
  (if (vector? node)
    (let [d (io/file parent (first node))]
      (mk-temp-dir d)
      (doall (map #(mk-tmpfs d %) (rest node)))
      d)
    (mk-temp-file (io/file parent node))))

(def deep-fs
     ["hozumi.test_rm_rf"
      ["src"
       ["org" "c.clj"]
       "b.clj"]
      ["lib" "A.jar"
       ["dev" "B.jar"]
       ["lab" ["lob"]]]
      "a.clj"])

(deftest test-rm-r
  (testing "make and delete dirs"
    (let [dir (mk-tmpfs (io/file (System/getProperty
				  "java.io.tmpdir"))
			deep-fs)]
      (is (.exists dir))
      (is (< 0 (count (.listFiles dir))))
      (rm-r dir :silently)
      (is (= 0 (count (.listFiles dir))))
      (is (not (.exists dir))))))