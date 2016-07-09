(defn p [] (p))

(defn test-evaluation-strategy
  [x y]
  (if (= x 0)
      0
      y))

(test-evaluation-strategy 0 (p))

; Explanation
;
; Let's define evaluation strategies which we have and their impact on the code execution.
;
; The first strategy is called applicative-order evaluation. In this case we compute arguments and only after that pass them into the procedure. Thus we should compute `0` which is zero itself and `(p)` which gives us an infinite recursive calls of itself.
;
; The second strategy is called normal-order evaluation. In this case we don't compute arguments at once. Instead, we substitute the expressions of operands in their place. So, our procedure transforms to this:
;
; (if (= 0 0)
;     0
;     (p))
;
; After that we compute all expressions from left to right starting with a special form `if`. Its predicate gives us `true` value so we get `0` as a result.
;
; Clojure as well as Lisp has applicative-order evaluation strategy. That's why we get a recursive calls of `(p)` and eventually `StackOverflowError` as the result of example. If you don't believe, try it yourself.
