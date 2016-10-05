// Compile this file on alpha.cs.iit.edu with gcc -lm Lab00_sample.c

// CS 350, Spring 2014
// Lab 0: Basic C Programming
//
// Illinois Institute of Technology, (c) 2014, James Sasaki

#include <stdio.h>  // to access printf, scanf

// Prototype for square root routine
//
double sqrt(double);

int main() {
  // In C, a string is an array of characters with one extra character '\0'
  // (the null character), which indicates the end of the string.
  // The printf format code %s prints a string.
  //
  char string1[6] = {'h', 'e', 'l', 'l', 'o', '\0'};
  char string2[6] = "hello";
  char empty1[1] = {'\0'};
  printf("%s\n", string1);
  printf("%s\n", string2);
  printf("empty1 ->%s<-empty1\n", empty1);
  
  // A character variable holds one individual character.
  // A character pointer (char *) holds the address of the first
  // character of a string.  The printf format %c prints a character.
  //
  char ch = '!';
  char *string3 = "This is a string";
  char *empty2 = "";
  printf("%s\n", string3);
  printf("empty2 ->%s<-empty2\n", empty1);
  
  // Use the %d format code to print an integer; use %f for a floating-
  // point or double precision number.
  // 
  int x = 17;
  double d = sqrt(x);
  printf("The square root of %d = %f\n", x, d);

  // To read into a variable of basic type (e.g., int, double, char),
  // use the same format codes as for printf, but insert an ampersand
  // before the variable in the list of variables.  We'll study the
  // ampersand in detail later, but basically it says to get the memory
  // address where y is stored.
  //
  int y;
  printf("Enter an integer >= 0 (and then return): ");
  scanf("%d", &y);

  // Refinements to the format codes: You can specify the width w of the field
  // to print using %wd or %wf.  For leading 0's, use %0d or %0wd.  For floats,
  // you can specify the number d of decimal places to print using %w.df or %.df.
  //
  double sqrt_y = sqrt(y);
  printf("The square root of %05d = %.3f = %10.4f\n", y, sqrt_y, sqrt_y);

  // If we read a value too large to be stored as an integer, we'll
  // get the wrong value stored, possibly a negative number.
  //
  printf("Now enter an integer >= 2147483648: ");
  scanf("%d", &y);
  sqrt_y = sqrt(y);  
  printf("The square root of %d = %f\n", y, sqrt_y);
  if (y < 0) {
    printf("\"nan\" means Not A Number (= sqrt of negative number)\n");
  }

  // To read/print a long integer, use format code %ld
  //
  long int z;
  printf("Try the same value (we'll read it as a long integer): ");
  scanf("%ld", &z);
  printf("The square root of %ld = %.10f\n", z, sqrt(z));

  return 0;
}
