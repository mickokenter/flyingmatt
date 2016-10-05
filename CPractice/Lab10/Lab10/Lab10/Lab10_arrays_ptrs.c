// CS 350, Spring 2014
// Lab 10: arrays_ptrs.c
//
// Illinois Institute of Technology, (c) 2014, James Sasaki
#include <stdio.h>
#define LEN 8

int main() {
	int b[LEN];
	int *p;

	// Set b = 1 3 5 7 9 11 13 15
	//
	int i;
	for (i = 0; i < LEN; i++) {
		b[i] = 2*i + 1;
	}

	// Print b, comma-separated
	//
	printf("%d", *b);
	for (i = 1; i < LEN; i++) {
		printf(", %d", *(b+i));
	}
	printf("\n");

	// Starting with the next-to-last element of b
	// and going leftwards, take the element to the
	// right and add it to the current element.
	//
	p = &b[LEN-2];
	while (&p[0] >= &b[0]) {
		p[0] = p[0] + p[1];
		p = &p[-1];
	}

	// Print b
	//
	printf("%d", *b);
	p = b+1;
	for (i = 1; i < LEN; i++) {
		printf(", %d", *p);
		p++;	// i.e., p = p+1;
	}
	printf("\n");
}

// Output
// 
// 1, 3, 5, 7, 9, 11, 13, 15
// 64, 63, 60, 55, 48, 39, 28, 15