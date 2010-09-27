(ns hozumi.rm-rf
  (:require [clojure.java.io :as io :only [delete-file]]))

(defn rm-rf [f & [silently]]
  (if (.isDirectory f)
    (dorun (map #(rm-rf % silently) (.listFiles f))))
  (io/delete-file f silently))