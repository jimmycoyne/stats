(ns jabberstats.views.common
  (:use noir.core
        hiccup.core
        hiccup.page-helpers))


(def includes {:jquery (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js")
                :default (include-css "/css/default.css")
               ;:reset (include-css "/css/reset.css")
               })



(defpartial build-head [incls]
            [:head
             [:title "Performance stats"]
             (map #(get includes %) incls)])

;;(defpartial layout [& content]
  ;          (html5
     ;         [:head
      ;         [:title "jabberstats"]
       ;        (include-css "/css/reset.css")]
        ;      [:body
         ;      [:div#wrapper
          ;      content]]))



(defpartial layout [& content]
            (html5
              (build-head [:reset :default :jquery :blog.js])
              [:body
               [:div#wrapper
                [:div.content
                 [:div#header
                  [:h1 (link-to "/blog/" "The stats blog")]]
                 content]]]))