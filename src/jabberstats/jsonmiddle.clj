(ns jabberstats.jsonmiddle
  (:require [clj-json.core :as json]))

(defn- json-request?
  [req]
  (if-let [#^String type (:content-type req)]
    (not (empty? (re-find #"^application/(vnd.+)?json" type)))))

(defn wrap-json-params [handler]
  (fn [req]
    (if-let [body (and (json-request? req) (:body req))]
      (let [bstr (slurp body)
            json-params (json/parse-string bstr)
            req* (assoc req
                   :json-params json-params
                   :params (merge (:params req) json-params))]
        (handler req*))
      (handler req))))



(defn parse-json-values [handler] 
  (fn [req] 
    (let [neue (if (= "application/json" (get-in req [:headers "content-type"])) 
              (update-in req [:params] assoc :stats-info  (json/parse-string (slurp (:body req)) true)) 
          req)] 
      (handler neue)))) 


(defn add-body [handler]
  (fn [req]
    (let [neue
          (update-in req [:params] assoc :req-body (slurp (:body req)))]
      (handler neue))))
