(ns simul-update.bad
  (:require [clojure.core.typed :as t]))

(t/defalias PrivateMessage
  (t/U (t/HMap :mandatory {:text t/Str
                           :user-id t/Num}
               :absent-keys #{:screen-name})
       (t/HMap :mandatory {:text t/Str
                           :screen-name t/Str}
               :absent-keys #{:user-id})))

;; has type errors from bogus test
(t/defn get-user [msg :- PrivateMessage]
  (if msg ;; bogus tests
    (do (t/ann-form (:user-id msg) t/Num)
        (t/ann-form (:screen-name msg) nil))
    (do (t/ann-form (:user-id msg) nil)
        (t/ann-form (:screen-name msg) t/Str))))
