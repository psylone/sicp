(defn +
  [a b]
  (if (= a 0)
      b
      (inc (+ (dec a)
              b))))

(prn (+ 4 5))

; Explanation
;
; Let's use the substitution model to compute this procedure for `4` and `5` operands:
;
; (+ 4 5)
; (inc (+ 3 5))
; (inc (inc (+ 2 5)))
; (inc (inc (inc (+ 1 5))))
; (inc (inc (inc (inc (+ 0 5)))))
; (inc (inc (inc (inc 5))))
; (inc (inc (inc 6)))
; (inc (inc 7))
; (inc 8)
; 9
;
; So, here's a recursive process. We expand our computation process first and then squeeze it to compute the result

(defn +
  [a b]
  (if (= a 0)
      b
      (+ (dec a) (inc b))))

(prn (+ 4 5))

; Let's use substitution model here:
;
; (+ 4 5)
; (+ 3 6)
; (+ 2 7)
; (+ 1 8)
; (+ 0 9)
; 9
;
; This is the iterative process. We don't expand and squeeze the computation process. Instead there's a repeating of the computation that stops when the condition `(= a 0)` will be true.
;
; So, the first procedure is based on a recursive process and the second one - on an iterative process.
