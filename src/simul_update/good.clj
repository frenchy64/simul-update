(ns simul-update.good
  (:require [clojure.core.typed :as t]))

(t/defalias PrivateMessage
  (t/U (t/HMap :mandatory {:text t/Str
                           :user-id t/Num}
               :absent-keys #{:screen-name})
       (t/HMap :mandatory {:text t/Str
                           :screen-name t/Str}
               :absent-keys #{:user-id})))

(t/defn get-user [msg :- PrivateMessage]
  (if (:user-id msg)
    (do (t/ann-form (:user-id msg) t/Num)        ;; (present due to if statement)
        (t/ann-form (:screen-name msg) nil))     ;; (absent due to if statement)
    (do (t/ann-form (:user-id msg) nil)          ;; (absent due to if statement)
        (t/ann-form (:screen-name msg) t/Str)))) ;; (present due to if statement)
