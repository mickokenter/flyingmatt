//XIAQIN QIU CS350 section 02

// CS 350, Spring 2014
// Lab 8: SDC Simulator
//
// Illinois Institute of Technology, (c) 2013, James Sasaki

#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// Prototypes
    void helpMsg(void);
	void initCPU(void);
    void readMemory(void);
    void dumpControlUnit(int pc, int ir, int regs[]);
    void dumpRegisters(int regs[]);
    void dumpMemory(int mem[]);
    void instruction_cycle(void);

// CPU declarations -- note we're being horrible and using global variables; after we
// see structures and pointers, we'll clean things up.
//
#define NBR_REGS 10
#define MEMLEN 100
    int running;          // true iff instruction cycle is active
	int regs[NBR_REGS];   // general-purpose registers
	int mem[MEMLEN];      // main memory
	int pc = 0;
	int ir;
	bool halt = false;

// Main program: Initialize the cpu, read initial memory values,
// and execute the read-in program starting at location 00.
//
int main(void) {
	printf("*** STUB *** CS 350 Lab 8, XIAQIN QIU\n");
	initCPU();
	readMemory();

	char prompt[3] = "> ";
	printf("\nBeginning execution:\n");
	printf("At the %sprompt, press return to execute the next instruction\n", prompt);
	printf("(or d to dump registers and memory, q to quit, or h or ? for help)\n");

	char command[80];       // User inputted command line
	char cmd_char;          // 'q' for quit etc.
	int simulator_quit = 0; // Have we seen simulator quit command?
	int nbr_read;           // Number of items read by sscanf
	bool command_done = false;
	
	while (!command_done) {
		printf("%s", prompt);
		fgets(command, sizeof command, stdin);   // Read through end of current line.
		//scanf("%s", command);
		nbr_read = sscanf(command, "%c", &cmd_char);
		if (command[0] != 0) {
			if(command[0] == '\n') {
				instruction_cycle();
				pc ++;
			}
			if(command[0] == 'd') {
				dumpControlUnit(pc, ir, regs);
				dumpMemory(mem);
			}
			else if(command[0] == 'h' || command[0] == '?') {
				helpMsg();
			}
			else if(command[0] == 'q') {
				command_done = true;
			}
			else{
				char* s;
				int c = strtol(command, &s, 10);
				int b;
				for(b = 0; b < c; b++) {
					instruction_cycle();
					pc ++;
				}
			}
		}
	}
	
	printf("Quitting\n\n");
	dumpControlUnit(pc, ir, regs);
	dumpMemory(mem);
	// When we see the quit command, dump the control unit and memory
	// and quit
	//
}


void initCPU(void) {

	int i;
	for (i = 0; i < NBR_REGS; i++){
		regs[i] = 0;
	}
	for (i = 0; i < MEMLEN; i++){
		mem[i] = 0;
	}
}

// Read and dump initial values for memory
//
void readMemory(void) {
	int loc = 0;
	printf("Read memory: At the prompt, enter the value for the indicated\n");
	printf("memory address.  Enter a number > 9999 or < -9999 when you're done.\n");

	while(1) {
		int d = 0;
		printf("Loc %02d : ", loc);
		scanf("%d", &d);
		if(d > 9999 || d < -9999 || loc >99){
			break;
		}
		mem[loc] = d;
		loc++;
	}
// *** You might need this after reading the sentinel ***

	char skip[2] = "\n\0";
	fgets(skip, sizeof skip, stdin);   // Clear the \n at end of terminating line

	printf("\nInitial value of memory:\n");
	dumpMemory(mem);
}

void dumpMemory(int mem[]) {
	int line, row;
	for(line = 0; line < 10; line++) {
		printf("  %d0:", line);
		for(row = 0; row < 10; row++) {
			int currLoc = line * 10 + row;
			printf("  %04d", mem[currLoc]);
		}
		printf("\n");
	}
}

void dumpControlUnit(int pc, int ir, int regs[]) {
	printf("Dumping\n");
	printf("  PC:  %04d  IR:  %04d\n\n", pc, ir);
	dumpRegisters(regs);
}


// dumpRegisters(regs): Print register values in two rows of five.
//
void dumpRegisters(int regs[]) {
	// *** STUB *** Hint: How do printf formats %d, %5d, %-5d, %05d, and % 05d differ?
	int regLoc;
	for(regLoc = 0; regLoc < 10; regLoc ++) {
		printf("  R%d:  %04d", regLoc, regs[regLoc]);
		if(regLoc == 4 || regLoc == 9)
			printf("\n");
	}
	printf("\n");
}

void helpMsg(void) {
	printf("Simulator commands:\nh or ? for help (prints thjis message)\nd to dump the control unit\nAninteger > 0 to execute that many instruction cycles\nOr just return, which executes one instruction cycle\n\nSDC Instruction set:\n 0xxx: HALT\n 1RMM: Load R <- M[MM]\n 2RMM: Store M[MM] <- R\n 3RMM: Add M[MM] to R\n 4Rxx: Negate R\n 5RMM: Load Immediate R <- MM\n 6RMM: Add Immediate R <- R + MM\n 7xMM: Branch to MM\n 8RMM: Branch Positive to MM if R > 0\n 90xx: Read char into R0\n 91xx: Print char in R0\n 92MM: Print string starting at MM\n 93MM: Dump control unit\n 94MM: Dump memory\n");
}

void instruction_cycle(void) {
	if(halt) {
		printf("Halted\n");
		return;
	}

	ir = mem[pc];
	int opcode = abs(ir / 1000);
	int regNum = abs(ir % 1000 / 100);
	int memNum = ir % 100;
	printf("At %02d instr %d %d %02d: ", pc, opcode, regNum, memNum);
	
	int temp = memNum;
	
	if(opcode == 9 && regNum == 0) {
		printf("GETC  Read char\n");
		printf("Enter a char (and press return): ");
		char c;
		scanf("%c", &c);
		printf("R0 <- %d", c);
		regs[0] = c;
	}
	
	if(opcode == 8) {
		char* posi;
		if(regs[regNum] > 0)
			posi = "Yes";
		else
			posi = "No";
		printf("BRP  pc <- %d if R%d = %d > 0: %s", memNum, regNum, regs[regNum], posi);
		if(regs[regNum] > 0)
			pc = memNum - 1;
	}
	
	switch(opcode) {
		case 0:
			printf("HALT");
			halt = true;
			break;
		case 1:
			printf("LD  R%d <- M[%02d] = %d", regNum, memNum, mem[memNum]);
			regs[regNum] = mem[memNum];
			break;
		case 2:
			printf("ST  MM[%02d] <- R%d = %d", memNum, regNum, regs[regNum]);
			mem[memNum] = regs[regNum];
			break;
		case 3:
			printf("ADD  R%d = R%d + MM[%02d] = %d + %d = ", regNum, regNum, memNum, regs[regNum], mem[memNum]);
			regs[regNum] += mem[memNum];
			regs[regNum] = regs[regNum] % 10000;
			printf("%d", regs[regNum]);
			break;
		case 4:
			printf("NEG  R%d <- -R%d = %d", regNum, regNum, -regs[regNum]);
			regs[regNum] = -regs[regNum];
			break;
		case 5:
			printf("LDM  R%d <- %d", regNum, memNum);
			regs[regNum] = memNum;
			break;
		case 6:
			printf("ADDM  R%d <- R%d + %d = %d + %d = ", regNum, regNum, memNum, regs[regNum], memNum);
			regs[regNum] += memNum;
			regs[regNum] = regs[regNum] % 10000;
			printf("%d", regs[regNum]);
			break;
		case 7:
			printf("BR  pc <- %d", memNum);
			pc = memNum - 1;
			break;
		case 8:
			break;
		case 9:
			switch(regNum) {
				case 0:
					break;
				case 1:
					printf("OUT  Print char in R0 (= %d): %c", regs[0], regs[0]);
					break;
				case 2:
					printf("PUTS  Print string: ");
					while(mem[temp] != 0) {
						printf("%c", mem[temp]);
						temp ++;
					}
					break;
				case 3:
					printf("DMP  Dump Control Unit:\n");
					dumpControlUnit(pc, ir, regs);
					break;
				case 4:
					printf("MEM  Dump Memory:\n");
					dumpMemory(mem);
					break;
				default:
					break;
			}
		default:
			break;
	}
	
	printf("\n");
	return;
}