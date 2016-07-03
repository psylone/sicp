(def e 10)
(prn e)

(def e (+ 5 3 4))
(prn e)

(def e (- 9 1))
(prn e)

(def e (/ 6 2))
(prn e)

(def e
  (+ (* 2 4)
     (- 4 6)))
(prn e)

(def a 3)
(def b (+ a 1))
(def e (+ a
          b
          (* a b)))
(prn e)

(def e (= a b))
(prn e)

(def e (if (and (> b a)
                (< b (* a b)))
           b
           a))
(prn e)

(def e (cond (= a 4) 6
             (= b 4) (+ 6 7 a)
             :else 25))
(prn e)

(def e (+ 2
          (if (> b a)
              b
              a)))
(prn e)

(def e (* (cond (> a b) a
                (< a b) b
                :else -1)
          (+ a 1)))
(prn e)
