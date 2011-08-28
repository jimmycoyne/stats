(ns jabberstats.views.stats
    (:require [jabberstats.views.common :as common]
              [noir.content.pages :as pages]
              [jabberstats.models.performance :as perf])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))


;(let [{name :name {:keys [pages isbn-10]} :details} book]
 ;        (println "name:" name "pages:" pages "isbn-10:" isbn-10))


(defpartial show-item [{:keys [name session _id id  site os]  {:keys [memory] }:perf :as rows  }]
   [:tr 
    [:th (link-to (str "get/sessiondetails/" session ) id) ] [:th site ]  [:th  os ]  [:th  memory ] ]
    )


(defpartial show-list [items]
   (println "show-list " )
            (common/layout
               [:table 
               [:tr [:th " Session "] [:th " Site " ] [:th " OS "] [:th " Memory "]]
               (map show-item items) ]))



(defpage [:post "/stats/add"] {:as post}
  (println post)
  (let [stats (post :stats-info)]
    (perf/insert  stats)
    (common/layout
    [:p "thank you for adding a user!1111111" ])))


(defpage [:get "/stats/get"] {:keys [perma]}
  (let [ results (perf/get-all)]
        (show-list results)))


(defpage [:get "/stats/getdistinct"] {:keys [perma]}
  (let [ results (perf/distinct-values)]
    (println  "distinct " results)
        (show-list results)))


(defpage [:get "/stats/get/sessiondetails/:id"] {:keys [id]}
  (let [result (perf/get-all-by-session-id id)]
    (println "get-stat " result)
    (show-list result)))




