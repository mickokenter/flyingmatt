//XIAQIN QIU CS350 section 02

#include <stdio.h>

int main() {
	unsigned int input = 0;	
	int left = 0;
	int right = 0;
	printf("XIAQIN QIU CS350 section 02\n\nEnter X in hex (0 to stop): ");		//prompt user to input a hex value
	scanf("%x", &value);
	while(input != 0) {	//input=0 to stop
		printf("Left and right endpoints: ");
		scanf("%d %d", &left, &right);
		if(left>=0 && rightEnd<=31 && left<=right) {	//check the value is legal
			input <<= left;	//set left endpoint
			input >>= (left+31-right);	//set right endpoint
			printf("X[%d:%d] = %#x = %u\n", left, right, input, input);
		}
		if(left < 0) {	//check the left endpoint
			printf("%d is an illegal endpoint\n", left);
		}				
		if(right > 31) {	//check the right endpoint
			printf("%d is an illegal endpoint\n", right);
		}
		if(left > right) {	//check the whether left endpoint is bigger than right endpoint
			printf("Left endpoint should be ≤ right endpoint\n");
		}
		printf("\nEnter X in hex (0 to stop): ");		//prompt user to input a hex value again
		scanf("%x", &input);
	}
}
