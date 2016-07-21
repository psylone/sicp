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
  [guess x]
  (< (abs (- (square guess) x))
     0.001))

(defn improve
  [guess x]
  (average guess (/ x guess)))

(defn new-if
  [predicate then-clause else-clause]
  (cond predicate then-clause
        :else else-clause))

; (prn (new-if (= 2 3) 0 5))

; (prn (new-if (= 1 1) 0 5))

(defn sqrt-iter
  [guess x]
  (new-if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))

(defn sqrt
  [x]
  (sqrt-iter 1.0 x))

(prn (sqrt 2))

; Explanation
;
; We will get `StackOverflowError` here. But wait, our `new-if` procedure works fine at the first sight. What's the matter?
;
; The reason for this behaviour in the applicative-order evaluation strategy. Following this strategy we should compute operands first and only after that apply the operator to them. Let's see it on example.
;
; Imagine we want to get the square root from 2. We do it eventually by the `sqrt-iter` procedure with `guess` equals to 1 and `x` equals to 2.
;
; During the first call we need to compute `new-if` operator and 3 operands: `(good-enough? guess x)`, `guess` and `(sqrt-iter (improve guess x) x)`.
;
; The first one is a combination which gives us `false` because our approximation is not accurate enough.
;
; The second one is just the `guess` value.
;
; The third one is a combination which calls `sqrt-iter` procedure again with a better approximation, so we get the second call of the `sqrt-iter` procedure.
;
; During the second call we need to do all the steps as before. Even if `(good-enough? guess x)` will give us `true` value we should compute all the rest operands because of applicative-order evaluation. So we get an infinite recursive calls of the `sqrt-iter` procedure. That's why we need in a special form `if` which computes only one operand of the rest depending on the condition.
