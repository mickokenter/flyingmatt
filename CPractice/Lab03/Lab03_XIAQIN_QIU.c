////XIAQIN QIU CS350 section 02

#include <stdio.h>
#include <strings.h>
#include <math.h>
#include <stdbool.h>

int main() {
	printf("CS350 Lab 3 for %s\n\n", "XIAQIN QIU CS350 section 02");	//name, section
	char input[32] = "";	//new array of char, size 32 max;
	do {
		printf("Input a bitstring or q for Quit\n");	//prompt input;
		scanf("%s", &input);
		if(input[0] == 'q'){  //q to quit;
			break;
		}
		int length = strlen(input);	//define the array length;
		int value = 0;	//decimal value;
		char complement[32] = "";	//2's negative complement's array;
		bool wInput = true;	//an index of a boolean;
		int i;
		for(i=0;i<length;i++) {	//get the decimal value;
			if(input[i] == '0' || input[i] == '1') {
				value = value * 2 + input[i] - '0';
			}
			else {
				wInput = false;
				printf("%c ", input[i]);	//output wrong input;
			}
		}
		if(!wInput){
			printf("\nIllegal inputs are shown above. Please input a Binary String.\n"); //complain;
		}
		if(length>1 && (input[0] == '1')) {	//other than 0 or 1 and 1 stands for negative in 2's complement;
			value = value - pow(2, length);	//get the value in 2's complement if it starts with 1;
		}
		else if(length==1){
			complement[0]=input[0];	//if only 1 number, directly get it;
		}
		if(wInput){
			bool flap = false;
			int j = 0;
			int temp = 0;
			for(j = length-1; j > 0; j--){	//find the 1 at most right place;
				if(input[j] == '1'){
					flap = true;
					temp = j;
					complement[j] = '1';	//assign 1 to the right most 1;
					break;
				}
				else{
					complement[j] = '0';	//assign 0 if no 1 found;
				}
			}
			if(flap){	//assign the number left;
				int k = 0;
				for(k = temp - 1; k >= 0; k--){
					if(input[k] == '1'){
						complement[k] = '0';
					}
					else {complement[k] = '1';}
				}
			}
			printf("The decimal value is: %d\nThe 2's complement negative of the bitstring is: %s\n\n", value, complement);
		}
	}while(input[0] != 'q');	//q to quit;
}
