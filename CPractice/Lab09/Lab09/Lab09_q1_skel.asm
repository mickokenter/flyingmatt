; CS 350, J. Sasaki, Illinois Institute of Technology
;
; power.asm
;
; Calculate 2^N where N >= 0 (and ^ means exponentiation).
; If N is too large, this calculation overflows.
;
; Properties of values:
;   0 ≤ x ≤ N and p * 2^x = 2^N  (These hold every time
;   we hit the loop test.)
;
; Register usage:
;   R... = p; R... = x (specify whichever registers you decide to use)
;
; Pseudocode:
;   p <- 1   ; Establish x and p initially
;   x <- N
; Loop  while x ≠ 0 do
;          p <- p + p
;          x <- x - 1
;   end Loop
;   result <- p
;   HALT
; N    .FILL  a value ≥ 0
; result .BLKW 1

        .ORIG    x3000
         *** STUB ***           ; p = 0
         *** STUB ***           ; p = 1
         *** STUB ***           ; x = N (loop count)

         *** STUB ***           ; while x ≠ 0
         *** STUB ***           ; . p = 2*p
         *** STUB ***           ; . x--
         *** STUB ***           ; end loop

         *** STUB ***           ; Result = p = 2^N
         HALT

N        *** STUB ***           ; The exponent; a value >= 0
Result   *** STUB ***           ; Gets set = 2^N
        .END
