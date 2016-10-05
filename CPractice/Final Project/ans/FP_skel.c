// *** Note: This file doesn't compile as is ***

// CS 350, Final Project, Spring 2014 (skeleton file)
//
// LC3 simulator

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Basic Declarations
//
	typedef short int Word;
	typedef unsigned short int Address;

	#define MEMLEN 65536
	#define NBR_REGS 8

	typedef struct {
		Word memory[MEMLEN];
		Word reg[NBR_REGS];  // Note: "register" is a reserved word
		int condition_code;  // positive, zero, or negative
		Word instr_reg;
		Address pgm_counter;
		int running;         // running = 1 iff CPU is executing instructions
	} CPU;


// Prototypes
//
	// CPU manipulation and status dump functions
	//
	void init_CPU(CPU *cpu);   // Simulate power-up
	void dump_CPU(CPU *cpu);
	void dump_control_unit(CPU *cpu);
	void dump_memory(CPU *cpu, int from, int to);
	char get_condition_code(CPU *cpu);
	void set_condition_code(CPU *cpu, Word value);

	// Instruction cycle functions
	//
	void instruction_cycle(CPU *cpu);      // Simulate entire instruction cycle
	void fetch_instr(CPU *cpu);            // Simulate fetch instruction part of cycle
	// *** STUB *** You may want other routines (you may not)

	// Functions for accessing parts of an instruction
	//
	int op(Word w);                       // Opcode of an instruction
	// *** STUB *** You probably want other routines

	// Functions for executing each instruction
	//
	void instr_ADD (CPU *cpu);
	void instr_AND (CPU *cpu);
	void instr_BR  (CPU *cpu);
	void instr_err (CPU *cpu);
	void instr_JMP (CPU *cpu);
//	void instr_JSR (CPU *cpu);
	void instr_LD  (CPU *cpu);
	void instr_LDI (CPU *cpu);
	void instr_LDR (CPU *cpu);
	void instr_LEA (CPU *cpu);
	void instr_NOT (CPU *cpu);
	void instr_RTI (CPU *cpu);
	void instr_ST  (CPU *cpu);
	void instr_STI (CPU *cpu);
	void instr_STR (CPU *cpu);
	void instr_TRAP(CPU *cpu);

	int  read_char();             // Read a character for GETC, IN
	void read_memory(CPU *cpu);
	void helpMsg();
	// *** STUB *** You may want other functions; depends on how you organize your code

// -------------------- MAIN PROGRAM --------------------
//
// The main program creates and initializes a CPU, loads a program,
// and executes it step by step (until it halts or the user quits).
// The CPU is dumped before and after executing the program
//
int main() {
	// Create and initialize the  CPU, read initial memory, dump CPU
	//
	CPU cpu_struct, *cpu;
	cpu = &cpu_struct;
	init_CPU(cpu);
	read_memory(cpu);
	
	char prompt[] = "> ";
	printf("\nBeginning execution:\n");
	printf("At the %sprompt, press return to execute the next instruction\n", prompt);
	printf("or a number, to execute that many instructions\n");
	printf("(or d to dump registers and memory, q to quit, or h or ? for help)\n");

	char cmd_char;         // 'q' for quit etc.
	int commands_done = 0;
	
	while (!commands_done) {
		printf("%s", prompt);
		cmd_char = read_char();
		
		if (cmd_char != 0) {
			if(cmd_char == '\n') {
				instruction_cycle(cpu);
			}else
			if(cmd_char == 'd') {
				dump_control_unit(cpu);
				dump_memory(cpu, 0, MEMLEN);
			}else
			if(cmd_char == 'h' || cmd_char == '?') 
				helpMsg();
			else
			if(cmd_char == 'q') 
				commands_done = 1;
			else{
				int b;
				for(b = 0; b < cmd_char; b++) {
					instruction_cycle(cpu);
				}
			}
		}
	}

	/* *** STUB *** */
}

// -------------------- CPU ROUTINES --------------------
//
//

// init_CPU(cpu) simulates power-on initialization: Program counter
// and instruction register are set to 0, as are memory and registers;
// condition code = Z, and the CPU will run the instruction cycle.
//
void init_CPU(CPU *cpu) {
	cpu -> pgm_counter = 0;
	cpu -> instr_reg = 0;
	cpu -> condition_code = 2;
	// *** STUB *** cpu -> condition_code = ???;   // Z condition code on power-up
	cpu -> running = 1;                // instruction cycle is running

	int regNbr;
	for (regNbr = 0; regNbr < NBR_REGS; regNbr++) {
		cpu -> reg[regNbr] = 0;
	}

	// Implementation note: You can't use a while loop
	// that checks for addr >= MEMLEN because addr is an
	// unsigned short and hence always <= MEMLEN.
	// (I.e., if addr = MEMLEN, then addr++ sets addr to 0.)
	//
	// In any case, we want the last word of memory to contain a HALT.
	//
	Address addr = 0;
	while (addr < MEMLEN-1) {
		cpu -> memory[addr++] = 0;
	}
	// *** STUB ***
}

// -------------------- INSTRUCTION ROUTINES --------------------

// Fetch instruction:
//   Copy instruction pointed to by program counter to instruction register
//   Increment program counter (wraparound addresses).
//
void fetch_instr(CPU *cpu) {
	cpu -> instr_reg = cpu -> memory[cpu -> pgm_counter];
	++(cpu -> pgm_counter);		// unsigned overflow will wraparound to 0
}

int op(Word w) {
	int opcode = w >> 12;
	return opcode;
}



// Execute an instruction cycle
//   Fetch an instruction
//   Decode the instruction opcode
//   Execute the instruction
//
void instruction_cycle(CPU *cpu) {
	// Get current instruction to execute and its location,
	// echo them out.
	//
	
	if(cpu -> running == 0) {
		printf("Halted\n");
		return;
	}
	
	Address old_pc = cpu -> pgm_counter;
	fetch_instr(cpu);
	printf("x%04hX: x%04hX  ", old_pc, cpu -> instr_reg);
	
	// Execute instruction; set cpu running = 0 if CPU execution
	// is to stop (because of TRAP HALT or an internal error).
	//
	
	int opcode = op(cpu -> instr_reg);
	
	switch (opcode) {
		case  0: 
			instr_BR(cpu);
			break;
		case  1:
			instr_ADD(cpu);
			break;
		case 2:
			instr_LD(cpu);
			break;
		case 3:
			instr_ST(cpu);
			break;
		case 5:
			instr_AND(cpu);
			break;
		case 6:
			instr_LDR(cpu);
			break;
		case 7:
			instr_STR(cpu);
			break;
		case 8:
			instr_RTI(cpu);
			break;
		case 9:
			instr_NOT(cpu);
			break;
		case 10:
			instr_LDI(cpu);
			break;
		case 11:
			instr_STI(cpu);
			break;
		case 12:
			instr_JMP(cpu);
			break;
		case 14:
			instr_LEA(cpu);
			break;
		case 15:
			instr_TRAP(cpu);
			break;
		default:
			printf("Bad opcode: %d; quitting\n", opcode);
			cpu -> running = 0;
	}
	
	
	printf("\n");
	return;
}

void instr_ADD (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int srcA = (cpu -> instr_reg) << 7;
	srcA = srcA >> 13;
	int condition = cpu -> instr_reg << 10;
	condition = condition >> 15;
	if(condition == 0) {
		int srcB = (cpu -> instr_reg) << 13;
		srcB = srcB >> 13;
		cpu -> reg[dst] = cpu -> reg[srcA] + cpu -> reg[srcB];
		printf("ADD  R%d <- R%d + R%d = %d\n", dst, srcA, srcB, (cpu -> reg[srcA] + cpu -> reg[srcB]));
	}else {
		int imm = (cpu -> instr_reg) << 11;
		imm = imm >> 11;
		cpu -> reg[dst] = cpu -> reg[srcA] + imm;
		printf("ADD  R%d <- R%d + %d = %d\n", dst, srcA, imm, (cpu -> reg[srcA] + imm));
	}
}
void instr_AND (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int src = (cpu -> instr_reg) << 7;
	src = src >> 13;
	int imm = (cpu -> instr_reg) << 11;
	imm = imm >> 11;
	cpu -> reg[dst] = (cpu -> reg[src]) & imm;
	printf("AND  R%d = R%d AND %d = %d\n", dst, src, imm, (cpu -> reg[src]) & imm);
}

void instr_err (CPU *cpu) {
	printf("bad trap vector!\n");
	cpu -> running = 0;
}

void instr_JMP (CPU *cpu) {
	int base = (cpu -> instr_reg) << 7;
	base = base >> 13;
	cpu -> pgm_counter = cpu -> reg[base];
	printf("JMP  PC <- R%d = %d", base, cpu -> reg[base]);
}

void instr_LD  (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int pcOffset = (cpu -> instr_reg) << 7;
	pcOffset = pcOffset >> 7;
	int location = (cpu -> pgm_counter) + pcOffset;
	int memValue = cpu -> memory[location];
	cpu -> reg[dst] = memValue;
	printf("LD  R%d <- M[%#x+%d] = M[%#x] = %#x\n", dst, (cpu -> pgm_counter), pcOffset, location, memValue);
}

void instr_LDI (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int pcOffset = (cpu -> instr_reg) << 7;
	pcOffset = pcOffset >> 7;
	cpu -> reg[dst] = cpu -> memory[cpu -> memory[(cpu -> pgm_counter) + pcOffset]];
	printf("LDI  R%d = M[M[%#x+%d]]\n", dst, (cpu -> pgm_counter), pcOffset);
}

void instr_LDR (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int base = (cpu -> instr_reg) << 7;
	base = base >> 13;
	int offset = (cpu -> instr_reg) << 10;
	offset = offset >> 10;
	int value = cpu -> memory[(cpu -> reg[base]) + offset];
	cpu -> reg[dst] = value;
	printf("LDR  R%d <- M[R%d+%d] = %d\n", dst, base, offset, value);
}

void instr_LEA (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int pcOffset = (cpu -> instr_reg) << 7;
	pcOffset = pcOffset >> 7;
	int value = (cpu -> pgm_counter) + pcOffset;
	cpu -> reg[dst] = value;
	printf("LEA  R%d <- %#x+%d = %#x\n", dst, (cpu -> pgm_counter), pcOffset, value);
}

void instr_NOT (CPU *cpu) {
	int dst = (cpu -> instr_reg) << 4;
	dst = dst >> 13;
	int src = (cpu -> instr_reg) << 7;
	src = src >> 13;
	cpu -> reg[dst] = -(cpu -> reg[src]) - 1;
	printf("NOT  R%d <- NOT R%d = %d\n", dst, src, (-(cpu -> reg[src]) - 1));
}

void instr_RTI (CPU *cpu) {
	printf("bad opcode: 8/n");
	cpu -> running = 0;
}

void instr_ST  (CPU *cpu) {
	int src = (cpu -> instr_reg) << 4;
	src = src >> 13;
	int pcOffset = (cpu -> instr_reg) << 7;
	pcOffset = pcOffset >> 7;
	int location = (cpu -> pgm_counter) + pcOffset;
	cpu -> memory[location] = cpu -> reg[src];
	printf("ST  M[%#x+%d] = M[%#x] <- R%d\n", (cpu -> pgm_counter), pcOffset, location, src);
}

void instr_STI (CPU *cpu) {
	int src = (cpu -> instr_reg) << 4;
	src = src >> 13;
	int pcOffset = (cpu -> instr_reg) << 7;
	pcOffset = pcOffset >> 7;
	cpu -> memory[cpu -> memory[(cpu -> pgm_counter) + pcOffset]] = cpu -> reg[src];
	printf("STI  M[M[%#x+%d]] <- R%d\n", (cpu -> pgm_counter), pcOffset, src);
}

void instr_STR (CPU *cpu) {
	int src = (cpu -> instr_reg) << 4;
	src = src >> 13;
	int base = (cpu -> instr_reg) << 7;
	base = base >> 13;
	int offset = (cpu -> instr_reg) << 10;
	offset = offset >> 10;
	cpu -> memory[(cpu -> reg[base]) + offset] = cpu -> reg[src];
	printf("STR  M[R%d+%d] <- R[%d]\n", base, offset, src);
}

void instr_TRAP(CPU *cpu) {
	int vector = (cpu -> instr_reg) << 8;
	vector = vector >> 8;
	if(vector == 32) {
		printf("GETC  Read char\n");
		printf("Enter a char (and press return): ");
		char c;
		scanf("%c", &c);
		printf("R0 <- %d", c);
		cpu -> reg[0] = c;
		char skip[1] = "\n";
		fgets(skip, sizeof skip, stdin);
	}else
	if(vector == 33) {
		printf("OUT  Print char in R0 (= %d): %c", cpu -> reg[0], cpu -> reg[0]);
	}else
	if(vector == 34) {
		printf("PUTS  Print string: ");
		int loc = cpu -> reg[0];
		while(cpu -> memory[loc] != 0) {
			printf("%c", cpu -> memory[loc]);
			loc ++;
		}
	}else
	if(vector == 35) {
		printf("IN  Read char\n");
		printf("Enter a char (and press return): ");
		char c;
		scanf("%c", &c);
		printf("R0 <- %d", c);
		cpu -> reg[0] = c;
		char skip[1] = "\n";
		fgets(skip, sizeof skip, stdin);
	}else
	if(vector == 36) {
		printf("bad trap vector!\n");
	}else
	if(vector == 37) {
		cpu -> running = 0;
		printf("HALT\n");
	}else {
		instr_err(cpu);
	}
	
}

// Execute branch instruction: Bitwise AND instruction's mask and
// cpu's condition code, branch iff result is nonzero.
//
// Echo kind of branch, current cc, whether or not the branch happened,
// and if so, the target of the branch.
//
void instr_BR(CPU *cpu) {
	int mask = cpu -> instr_reg;
	mask = mask << 4;
	mask = mask >> 13; 
	int branch = (cpu -> condition_code) & mask;
	if(branch) {
		int pcOffset = cpu -> instr_reg;
		pcOffset = pcOffset << 7;
		pcOffset = pcOffset >> 7;
		cpu -> pgm_counter += pcOffset;
		printf("BR instruction; PC = PC + %d = %d\n", pcOffset, cpu -> pgm_counter);
	}
	else {
		cpu -> running = 0;
		printf("BR instruction; HALT");
	}
}

/* *** STUB *** */


// Read and return a character from standard input.  User must
// enter return after the char.  Just pressing return causes '\n'
// to be returned.  Any extra characters after the first are ignored.
//

void read_memory(CPU *cpu) {
	// Prompt user for file name; note fgets includes the
	// end-of-line \n in the buffer, so we have to remove it.
	// (Exception: If the buffer gets filled, fgets stops
	// reading and doesn't insert \n.)
	//
	printf("File to read from (default is program.hex): ");
	#define RM_BUFFER_LEN 100
	char filename[RM_BUFFER_LEN] = "";
	fgets(filename, sizeof filename, stdin);
	int filename_len = strlen(filename);
	if (filename_len == 1) {    // User just pressed \n
		strcpy(filename,"program.hex");
	}
	else {
		filename[filename_len-1] = '\0';  // drop trailing \n
	}

	// Open the file and read the first line, which should contain
	// the origin (the memory location where the rest of the file
	// should be loaded).
	//
	FILE *input_file = fopen(filename, "rt");
	fscanf(input_file, "%hx", &cpu -> pgm_counter);

	// Read the rest of the file and load it into memory,
	// starting at the origin.  We're looking for a sequence of
	// 4-digit hex numbers
	//
	Address location = cpu -> pgm_counter;
	int nbr_read = fscanf(input_file, "%hx", &cpu -> memory[location++]);
	while (nbr_read == 1) {
		printf("Read %hx\n", cpu -> memory[location-1]);
		nbr_read = fscanf(input_file, "%hx", &cpu -> memory[location++]);
	}
	fclose(input_file);
}



int read_char() {
	char buffer[3] = "";
	fgets(buffer, sizeof buffer, stdin);
	return buffer[0];
}

void helpMsg() {
	printf("Simulator commands:\nh or ? for help (prints thjis message)\nd to dump the control unit\nAninteger > 0 to execute that many instruction cycles\nOr just return, which executes one instruction cycle\n\nSDC Instruction set:\n 0xxx: HALT\n 1RMM: Load R <- M[MM]\n 2RMM: Store M[MM] <- R\n 3RMM: Add M[MM] to R\n 4Rxx: Negate R\n 5RMM: Load Immediate R <- MM\n 6RMM: Add Immediate R <- R + MM\n 7xMM: Branch to MM\n 8RMM: Branch Positive to MM if R > 0\n 90xx: Read char into R0\n 91xx: Print char in R0\n 92MM: Print string starting at MM\n 93MM: Dump control unit\n 94MM: Dump memory\n");
}

// dump_control_unit(cpu): Dump the control unit (pc, ir, and data registers).
//
void dump_control_unit(CPU *cpu) {
	printf("Dumping\n");
	printf("  PC:  %04d  IR:  %04d\n\n", cpu->pgm_counter, cpu->instr_reg);
	dump_CPU(cpu);
}

// dumpRegisters(cpu): Print register values in two rows of five.
//
void dump_CPU(CPU *cpu) {
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
void dump_memory(CPU *cpu, int from, int to) {
	int line, row, currLoc;
	for(line = 0; line < 10; line ++) {
		printf("  %d0:", line);
		for(row = 0; row < 10; row ++) {
			currLoc = line * 10 + row;
			if (currLoc > to)
				break;
			printf("  %04d", cpu->memory[currLoc]);
		}
		printf("\n");
		if (currLoc > to)
			break;
	}
}


/* *** STUB *** */
