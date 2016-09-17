(defn square
  [x]
  (* x x))

(defn abs
  [x]
  (if (< x 0)
      (- x)
      x))

(defn average
  [x y]
  (/ (+ x y) 2))

(defn good-enough?
  [guess next-guess]
  (< (/ (abs (- next-guess guess))
        next-guess)
     0.001))

(defn improve
  [guess x]
  (/ (+ (* 2 guess)
        (/ x (square guess)))
     3))

(defn curt-iter
  [guess x]
  (def next-guess (improve guess x))
  (if (good-enough? guess next-guess)
      next-guess
      (curt-iter next-guess x)))

(defn curt
  [x]
  (if (zero? x)
      x
      (curt-iter 1.0 x)))

(prn (curt 125))

; Explanation
;
; So far as our program consists of several separate procedures, we need to change only the `improve` procedure to calculate the cube root of the value (except a few names of procedures of course).
