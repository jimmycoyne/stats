(ns jabberstats.db
    (:require [somnium.congomongo :as db]))
  
(db/mongo!  
  :db "mydb") 

(defn init []
      (println " db name is init")
      (db/mongo!  :db "test") )


(defn insert! [k stats]
   (db/insert! k stats))

(defn get-all[k]
   (db/fetch  k ))

(defn get-all-grouped [groupedby coll]
   (db/fetch  groupedby coll ))


(defn get-by-id[coll id]
  (db/fetch-one coll :where {:_id (db/object-id id) }))

(defn get-all-by[coll k v]
  (db/fetch coll :where {k v }))

(defn distinct-values [collection k]
 ( db/distinct-values collection k))


