(defn a-plus-abs-b
  [a b]
  ((if (> b 0) + -) a b))

(prn (a-plus-abs-b 12 -1 ))

; Explanation
;
; We have a compound `a-plus-abs-b` procedure here. It accepts two parameters `a`, `b` and consists of a single combination `((if (> b 0) + -) a b)`. This combination contains one operator and two operands. It's interesting because the operator, in turn, is a compound expression itself which is a special form `if`.
;
; Following the rule of computing combinations in an applicative-order evaluation we should compute each expression inside combination. So we compute a special form `(if (> b 0) + -)` which is operator and then we compute the whole combination.
;
; Let's consider an example. If the parameters have the values `12` and `-1` then the first step is to compute a special form `if`: `(if (> -1 0) + -)` which is `-`. The second step is to compute the whole combination: `(- 12 -1)` which gives us `13`.
