(ns jabberstats.views.welcome
  (:require [jabberstats.views.common :as common]
            [noir.content.pages :as pages])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to jabberstats ... "]))






(defpage [:post "/user/test"] {:as body}
  (println body)
  (let [subject (body :backbone)]
    (println subject))
  (common/layout
    [:p "thank you for adding a user!" ]))
