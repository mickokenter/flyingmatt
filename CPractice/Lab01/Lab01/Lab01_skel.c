// version 2014-01-23

// ** Skeleton ** <-- remove this line and complete the program
// *** Your name and section (replace this with the correct information ***
// (Also rename this file to Lab01_YourName_Section.c and remove this comment line)
// (Work on alpha.cs.iit.edu; compile with gcc filename.c -lm.)

// CS 350, Spring 2014
// Lab 1: Fix syntax & semantics errors
//
// Illinois Institute of Technology, (c) 2014, James Sasaki

#include <stdio.h>	// to access printf, scanf

int main() {
	printf("CS 350 Lab 1 for %s\n\n", "***Fill in your name and section ***");  // <-- Fill in your name and section and remove this comment

	int n = 5, a[5] = {1, 3, 5, 7, 9};	// Declare & initialize array of length 5
	int sum;

	for (int i = 1; i <= n; i++) {		// Sum up the 5 elements of the array
		sum = sum + a[i];


	printf("Enter an integer x: %d");	// Prompt the user
	int x
	scanf("%d", &x);					// Read in the integer

	// Print out the sum, the division we're performing, and the result (without truncation)
	// E.g., The sum of the array is 25; 25/2 = 12.500000
	//
	printf("The sum of the array is $d; %d/%d = %f\n", sum, sum, sum / x);
		
	// Declare an integer variable y and initialize it using a hexadecimal constant.
	// Print y in decimal, hex, and with leading zeros so that we get the output
	// y = 4011 = fab = 0xfab =   fab = 0000fab
	//
	int y = 0xfab;
	printf("y = %d = %x\n", y, y, y, y, y);
	return 0;
}
