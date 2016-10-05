		.ORIG	x3000
		LEA		Y1, 0
		LDR		Y2, X, 0
		LDR		R1, N, 0

Loop	BRNZ	Done
		ADD		Y1, Y1, Y1
		LDR		Y2, Y2, 0
		BRN		IncY1
		BR		subloop

Subloop	ADD		Y2, Y2, Y2
		ADD		R1, R1, -1
		BR		Loop

Done	HALT

IncY1	ADD		Y1, Y1, 1
		BR		Subloop
         
X		.FILL#	xFFFF
Y1		.BLKW#	1
Y2		.BLKW#	1
N		.FILL#	5
