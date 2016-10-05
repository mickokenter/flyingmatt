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

		.ORIG	x3000
		LEA		R0, 0          ; p = 0
		ADD		R2, R2, 1      ; p = 1
		LEA		R1, N          ; x = N (loop count)

Loop	BRZ		Done           ; while x ≠ 0
		ADD		R0, R0, R0     ; . p = 2*p
		ADD		R1, R1, -1     ; . x--
		BR		Loop           ; end loop

Done	LDR		Result, R0, 0  ; Result = p = 2^N
		HALT

N		.FILL	3              ; The exponent; a value >= 0
Result	.BLKM	1              ; Gets set = 2^N
		.END

