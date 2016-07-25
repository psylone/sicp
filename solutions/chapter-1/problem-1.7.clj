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

(defn sqrt-iter
  [guess x]
  (if (good-enough? guess x)
      guess
      (sqrt-iter (improve guess x) x)))

(defn sqrt
  [x]
  (sqrt-iter 1.0 x))

; (prn (sqrt 0.00057))
; (prn (sqrt 57999999999999))

; Explanation

; We have two interesting examples here. The first one gives us result which is not so accurate. The second one throws `StackOverflowError`. We can see this weird behaviour when trying to compute very small or conversely large values. This is due to the fact that all calculations performed by the computer have a limited precision.
;
; Keeping this in mind we can try to find a way to increase efficiency of the algorithm. And we can do this!
;
; Let's take a detailed look at our `good-enough?` procedure. Actually it also depends on precision of the calculation. But how can we avoid it?
;
; One of the methods is to track of how the approximation will vary and stop the process when this change will be a small part of the approximation itself. In other words - to use relative error. Using this approach let's implement three new procedures: `precise-good-enough?`, `precise-sqrt-iter` and `precise-sqrt`. Pay attention that `0.001` becomes a relative value.

(defn precise-good-enough?
  [guess next-guess]
  (< (/ (abs (- next-guess guess))
        next-guess)
  0.001))

(defn precise-sqrt-iter
  [guess x]
  (def next-guess (improve guess x))
  (if (precise-good-enough? guess next-guess)
      next-guess
      (precise-sqrt-iter next-guess x)))

(defn precise-sqrt
  [x]
  (precise-sqrt-iter 1.0 x))

; (prn (precise-sqrt 0.00057))
; (prn (precise-sqrt 57999999999999))

; Now we can see that both examples give a much more accurate result. But there's another pitfall. Just try to compute a square root from zero with our new `precise-sqrt` procedure. You will get an `ArithmeticException: Divide by zero`. On the other hand the old `sqrt` procedure gives us more or less correct result. What the heck is that?

; (prn (sqrt 0))
; (prn (precise-sqrt 0))

; Let's explore the behaviour of our procedures in computing the square root from zero value. The procedure `improve` takes the form `(average guess (/ 0 guess))` which is means that every next approximation will be exactly a half of the previous one. The further aspect of this study is `precise-good-enough?` procedure. Taking into account the relationship between the next approximation and the previous one, we can see that the relative error remains independent from the approximation value from iteration to iteration. In fact it's just `1`. It means that `precise-good-enough?` procedure will always return `false` so we find ourselves in an infinite recursive calls of the `precise-sqrt-iter`.
;
; That's great, but why do we get `ArithmeticException: Divide by zero` instead of `StackOverflowError`? The reason for this is that sooner or later approximation value reaches zero value and the relative error will be computed like this: `(/ (abs (- 0 guess)) 0)` where `guess` is the previous approximation value. To avoid this error we can return `0` when we want to calculate the square root of it. Thus our `precise-sqrt` procedure gets a new look:

(defn precise-sqrt
  [x]
  (if (zero? x)
      x
      (precise-sqrt-iter 1.0 x)))

; That's it! All examples are now look well and give accurate results. It's time to have a cup of coffee.

(prn (precise-sqrt 0.00057))
(prn (precise-sqrt 57999999999999))
(prn (precise-sqrt 0))
