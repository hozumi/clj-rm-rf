(ns hozumi.rm-rf
  (:require [clojure.java.io :as io :only [delete-file]]))

(defn rm-r [f & [silently]]
  (if (.isDirectory f)
    (dorun (map #(rm-r % silently) (.listFiles f))))
  (io/delete-file f silently))