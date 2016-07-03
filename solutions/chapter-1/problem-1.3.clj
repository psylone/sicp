(defn square
  [x]
  (* x x))

; (prn (square 9))

(defn sum-of-squares
  [x y]
  (+ (square x)
     (square y)))

; (prn (sum-of-squares 4 5))

(defn max-sum-of-squares
  [a b c]
  (cond (and (< a b) (< a c)) (sum-of-squares b c)
        (and (< b c) (< b a)) (sum-of-squares a c)
        (and (< c a) (< c b)) (sum-of-squares a b)))

(prn (max-sum-of-squares 1 2 3))
(prn (max-sum-of-squares 2 1 3))
(prn (max-sum-of-squares 3 2 1))
