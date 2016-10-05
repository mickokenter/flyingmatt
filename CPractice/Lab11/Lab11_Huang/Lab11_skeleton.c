// ** Skeleton ** <-- remove this line and complete the program
// *** Your name and section (replace this with the correct information ***
// (Also rename this file to Lab11_YourName_Section.c and remove this comment line)

// CS 350, Spring 2014
// Lab 11: SDC simulator using structures and pointers
//
// Illinois Institute of Technology, (c) 2014, James Sasaki

#include <stdio.h>
#include <string.h>

// CPU Declarations -- a CPU is a structure with fields for the
// different parts of the CPU.
//
	typedef int Word;
	typedef int Address;

	#define MEMLEN 100
	#define NBR_REGS 10

	typedef struct {
		Word mem[MEMLEN];
		Word reg[NBR_REGS];  // Note: "register" is a reserved word
		Address pc;          // Program Counter
		Word ir;             // Instruction Register
		int running;         // running = 1 iff CPU is executing instructions
	} CPU;

// Prototypes
    void helpMsg(void);
	void initCPU(CPU *cpu);
    void readMemory(CPU *cpu);
    void dumpControlUnit(CPU *cpu);
    void dumpRegisters(CPU *cpu);
    void dumpMemory(CPU *cpu);
    void instruction_cycle(CPU *cpu);

// Main program: Initialize the cpu, read initial memory values,
// and execute the read-in program starting at location 00.
//
int main(void) {
	printf("*** STUB *** CS 350 Lab 11, Your name\n");
	CPU cpu_value;
	CPU *cpu = &cpu_value;
	initCPU(cpu);
	readMemory(cpu);

	char prompt[] = "> ";
	printf("\nBeginning execution:\n");
	printf("At the %sprompt, press return to execute the next instruction\n", prompt);
	printf("or a number, to execute that many instructions\n");
	printf("(or d to dump registers and memory, q to quit, or h or ? for help)\n");

	char command[80];       // User inputted command line
	char cmd_char;          // 'q' for quit etc.
	int simulator_quit = 0; // Have we seen simulator quit command?
	int nbr_read;           // Number of items read by sscanf
	int commands_done = 0;
	
	while (!commands_done) {
		printf("%s", prompt);
		fgets(command, sizeof command, stdin);   // Read through end of current line.
		nbr_read = sscanf(command, "%c", &cmd_char);

		if (command[0] != 0) {
			if(command[0] == '\n') {
				instruction_cycle(cpu);
				cpu_value.pc ++;
			}
			if(command[0] == 'd') {
				dumpControlUnit(cpu);
				dumpMemory(cpu);
			}else
			if(command[0] == 'h' || command[0] == '?') 
				helpMsg();
			else
			if(command[0] == 'q') 
				commands_done = 1;
			else{
				char* s;
				int c = strtol(command, &s, 10);
				int b;
				for(b = 0; b < c; b++) {
					instruction_cycle(cpu);
					cpu->pc ++;
				}
			}
		}
	}
	
	// When we see the quit command, dump the control unit and memory
	// and quit
	//
	printf("Quitting\n\n");
	dumpControlUnit(cpu);
	dumpMemory(cpu);
}

// Print out the instruction set architecture for the SDC.
//
void helpMsg(void) {
	printf("Simulator commands:\nh or ? for help (prints thjis message)\nd to dump the control unit\nAninteger > 0 to execute that many instruction cycles\nOr just return, which executes one instruction cycle\n\nSDC Instruction set:\n 0xxx: HALT\n 1RMM: Load R <- M[MM]\n 2RMM: Store M[MM] <- R\n 3RMM: Add M[MM] to R\n 4Rxx: Negate R\n 5RMM: Load Immediate R <- MM\n 6RMM: Add Immediate R <- R + MM\n 7xMM: Branch to MM\n 8RMM: Branch Positive to MM if R > 0\n 90xx: Read char into R0\n 91xx: Print char in R0\n 92MM: Print string starting at MM\n 93MM: Dump control unit\n 94MM: Dump memory\n");
}


// Initialize cpu registers, memory, pc, and ir (all to zeros).
// Initialize the running flag to true.
//
void initCPU(CPU *cpu) {
	int i;
	for (i = 0; i < NBR_REGS; i++)
		cpu->reg[i] = 0;
	for (i = 0; i < MEMLEN; i++)
		cpu->mem[i] = 0;
	cpu->pc = 0;
	cpu->ir = 0;        // Might as well initialize it to something.
	cpu->running = 1;
}

// Read and dump initial values for memory
//
void readMemory(CPU *cpu) {
	int loc = 0;
	printf("Read memory: At the prompt, enter the value for the indicated\n");
	printf("memory address.  Enter a number > 9999 or < -9999 when you're done.\n");

	while(1) {
		int d = 0;
		printf("Loc %02d : ", loc);
		scanf("%d", &d);
		if(d > 9999 || d < -9999 || loc >99)
			break;
		cpu->mem[loc] = d;
		loc ++;
	}

	char skip[2] = "\n\0";
	fgets(skip, sizeof skip, stdin);   // Clear the \n at end of terminating line

	printf("\nInitial value of memory:\n");
	dumpMemory(cpu);
}


// dumpControlUnit(cpu): Dump the control unit (pc, ir, and data registers).
//
void dumpControlUnit(CPU *cpu) {
	printf("Dumping\n");
	printf("  PC:  %04d  IR:  %04d\n\n", cpu->pc, cpu->ir);
	dumpRegisters(cpu);
}

// dumpRegisters(cpu): Print register values in two rows of five.
//
void dumpRegisters(CPU *cpu) {
	int regLoc;
	for(regLoc = 0; regLoc < 10; regLoc ++) {
		printf("  R%d:  %04d", regLoc, cpu->reg[regLoc]);
		if(regLoc == 4 || regLoc == 9)
			printf("\n");
	}
	printf("\n");
}

// dumpMemory(cpu): Print memory values in a table, ten per row,
// with a space between each group of five columns and with a header
// column of memory locations.
//
void dumpMemory(CPU *cpu) {
	int line, row;
	for(line = 0; line < 10; line ++) {
		printf("  %d0:", line);
		for(row = 0; row < 10; row ++) {
			int currLoc = line * 10 + row;
			printf("  %04d", cpu->mem[currLoc]);
		}
		printf("\n");
	}
}


// Execute one instruction cycle.
//
void instruction_cycle(CPU *cpu) {
	int halt = 0;
	if(halt) {
		printf("Halted\n");
		return;
	}

	cpu->ir = cpu->mem[cpu->pc];
	int opcode = abs(cpu->ir / 1000);
	int regNum = abs(cpu->ir % 1000 / 100);
	int memNum = cpu->ir % 100;
	printf("At %02d instr %d %d %02d: ", cpu->pc, opcode, regNum, memNum);
	
	int temp = memNum;
	
	if(opcode == 9 && regNum == 0) {
		printf("GETC  Read char\n");
		printf("Enter a char (and press return): ");
		char c;
		scanf("%c", &c);
		printf("R0 <- %d", c);
		cpu->reg[0] = c;
	}
	
	if(opcode == 8) {
		char* posi;
		if(cpu->reg[regNum] > 0)
			posi = "Yes";
		else
			posi = "No";
		printf("BRP  pc <- %d if R%d = %d > 0: %s", memNum, regNum, cpu->reg[regNum], posi);
		if(cpu->reg[regNum] > 0)
			cpu->pc = memNum - 1;
	}
	
	switch(opcode) {
		case 0:
			printf("HALT");
			halt = 1;
			break;
		case 1:
			printf("LD  R%d <- M[%02d] = %d", regNum, memNum, cpu->mem[memNum]);
			cpu->reg[regNum] = cpu->mem[memNum];
			break;
		case 2:
			printf("ST  MM[%02d] <- R%d = %d", memNum, regNum, cpu->reg[regNum]);
			cpu->mem[memNum] = cpu->reg[regNum];
			break;
		case 3:
			printf("ADD  R%d = R%d + MM[%02d] = %d + %d = ", regNum, regNum, memNum, cpu->reg[regNum], cpu->mem[memNum]);
			cpu->reg[regNum] += cpu->mem[memNum];
			cpu->reg[regNum] = cpu->reg[regNum] % 10000;
			printf("%d", cpu->reg[regNum]);
			break;
		case 4:
			printf("NEG  R%d <- -R%d = %d", regNum, regNum, -cpu->reg[regNum]);
			cpu->reg[regNum] = -cpu->reg[regNum];
			break;
		case 5:
			printf("LDM  R%d <- %d", regNum, memNum);
			cpu->reg[regNum] = memNum;
			break;
		case 6:
			printf("ADDM  R%d <- R%d + %d = %d + %d = ", regNum, regNum, memNum, cpu->reg[regNum], memNum);
			cpu->reg[regNum] += memNum;
			cpu->reg[regNum] = cpu->reg[regNum] % 10000;
			printf("%d", cpu->reg[regNum]);
			break;
		case 7:
			printf("BR  pc <- %d", memNum);
			cpu->pc = memNum - 1;
			break;
		case 8:
			break;
		case 9:
			switch(regNum) {
				case 0:
					break;
				case 1:
					printf("OUT  Print char in R0 (= %d): %c", cpu->reg[0], cpu->reg[0]);
					break;
				case 2:
					printf("PUTS  Print string: ");
					while(cpu->mem[temp] != 0) {
						printf("%c", cpu->mem[temp]);
						temp ++;
					}
					break;
				case 3:
					printf("DMP  Dump Control Unit:\n");
					dumpControlUnit(cpu);
					break;
				case 4:
					printf("MEM  Dump Memory:\n");
					dumpMemory(cpu);
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
