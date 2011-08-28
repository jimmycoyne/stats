(ns jabberstats.models.performance
  (:require [jabberstats.db :as db]) )


;(defn- store! [{username :username :as user}]
 ; (db/update! :users assoc username user))

 
 (defn insert [stats]
   (db/insert! :performance stats))


(defn get-by-id[id]
  (println "get-stat " id )
  (db/get-by-id :performance id))

(defn get-all-by-session-id [id]
  (println "get-stat " id )
  (db/get-all-by :performance :session id))



(defn get-all[]
   (db/get-all :performance ))

(defn get-all-grouped[]
   (db/get-all-grouped :performance :session))


(defn distinct-values []
   (db/distinct-values :performance "session"  { "sesion" "0006" }))



