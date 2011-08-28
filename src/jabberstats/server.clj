(ns jabberstats.server
  (:require [noir.server :as server]
            [jabberstats.jsonmiddle :as jsonmiddle]
            [jabberstats.db  :as db]))

(server/load-views "src/jabberstats/views/")

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    
    (server/add-middleware jsonmiddle/parse-json-values)
    (db/init)
    (server/start port {:mode mode
                        :ns 'jabberstats})))


;(-main)

;(.printStackTrace *e) 