(ns ansi.core
  (:require [clojure.string :as str])
  (:gen-class))

(def escape "\033")
; (def ansi-escape "\e")
; (def ansi-escape "\x1B")

(def start "[")
(def end   "m")

(def ansi-codes
  {:bold          "1"
   :dim           "2"
   :underlined    "4"
   :blink         "5"
   :reverse       "7"
   :hidden        "8"
   :default       "39"
   :black         "30"
   :red           "31"
   :green         "32"
   :yellow        "33"
   :blue          "34"
   :magenta       "35"
   :cyan          "36"
   :light-gray    "37"
   :dark-gray     "90"
   :light-red     "91"
   :light-green   "92"
   :light-yellow  "93"
   :light-blue    "94"
   :light-magenta "95"
   :light-cyan    "96"
   :white         "97"
   :bg-default       "49"
   :bg-black         "40"
   :bg-red           "41"
   :bg-green         "42"
   :bg-yellow        "43"
   :bg-blue          "44"
   :bg-magenta       "45"
   :bg-cyan          "46"
   :bg-light-gray    "47"
   :bg-dark-gray     "100"
   :bg-light-red     "101"
   :bg-light-green   "102"
   :bg-light-yellow  "103"
   :bg-light-blue    "104"
   :bg-light-magenta "105"
   :bg-light-cyan    "106"
   :bg-white         "107"
   :reset-all        "0"
   :reset-bold       "21"
   :reset-dim        "22"
   :reset-underlined "24"
   :reset-blink      "25"
   :reset-reverse    "27"
   :reset-hidden     "28"})

(defn- attr-str
  [& attributes]
  (str escape
       start
       (str/join ";" (map #(% ansi-codes) attributes))
       end))

(defn ansi-str
  [text & attributes]
  (apply str (apply attr-str attributes)
             text
             (attr-str :reset-all)))

(defn ansi-print
  [text & attributes]
  (print (apply ansi-str text attributes)))

(defn ansi-println
  [text & attributes]
  (println (apply ansi-str text attributes)))

