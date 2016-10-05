//XIAQIN QIU CS350 section 02


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
	void dump_memory(CPU *cpu, Address from, Address to);
	char get_condition_code(CPU *cpu);
	void set_condition_code(CPU *cpu, Word value);
    void helpMsg();
    void read_memory(CPU *cpu);
    
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
	void instr_JSR (CPU *cpu);
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
	int  intValueOf(short a);
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
	printf("\nBeginning execution:\nEnter to run one line\nInput > 0 to run numbers of input lines\nInput h or ? for help / d to dump register and memory / q for quit\n");
    char input[16];
    int flag = 1;
    while (flag) {
        printf("> ");
        fgets(input, sizeof input, stdin);
        int chr=input[0];
        if(chr=='h' || chr=='?'){ // h or ? for help
            helpMsg();
        }
        else if(chr=='d'){ // d to dump register and memory
            dump_control_unit(cpu);
        }
        else if(chr=='q'){ // q to quit
            cpu -> running = 0;
            flag = 0;
            printf("Halted\n");
        }
        else if(chr>47 && chr<58){ // check numbers
            if(cpu -> running == 0){
                printf("Halted, CPU not running\n");
            }
            else {
                int i;
                int t=0; // count
                for (i = 0; i<strlen(input)-1; i++) {
                    t=t*10+input[i]-'0';
                }
                for (i = 0; i<t; i++) {
                    instruction_cycle(cpu);
                }
            }
        }
        else if(chr == 10){
            if(cpu -> running == 0){
                printf("Halted, CPU not running\n");
            }
            else{
                instruction_cycle(cpu);
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
	// *** STUB *** cpu -> condition_code = ???;   // Z condition code on power-up
    cpu -> condition_code = 2;
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
    read_memory(cpu);
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

// Execute an instruction cycle
//   Fetch an instruction
//   Decode the instruction opcode
//   Execute the instruction
//
void instruction_cycle(CPU *cpu) {
	// Get current instruction to execute and its location,
	// echo them out.
	//
	Address old_pc = cpu -> pgm_counter;
	fetch_instr(cpu);
	printf("x%04hX: x%04hX  ", old_pc, cpu -> instr_reg);

	// Execute instruction; set cpu running = 0 if CPU execution
	// is to stop (because of TRAP HALT or an internal error).
	//
	switch (op(cpu -> instr_reg)) {
		case  0: instr_BR(cpu);  break;
        case  1: instr_ADD(cpu); break;
        case  2: instr_LD(cpu);  break;
        case  3: instr_ST(cpu);  break;
        case  4: instr_JSR(cpu); break;
        case  5: instr_AND(cpu); break;
        case  6: instr_LDR(cpu); break;
        case  7: instr_STR(cpu); break;
        case  8: instr_RTI(cpu); break;
        case  9: instr_NOT(cpu); break;
        case 10: instr_LDI(cpu); break;
        case 11: instr_STI(cpu); break;
        case 12: instr_JMP(cpu); break;
        case 13: instr_err(cpu); break;
        case 14: instr_LEA(cpu); break;
        case 15: instr_TRAP(cpu);break;
	/* *** STUB *** */

	default:
		printf("Bad opcode: %d; quitting\n", op(cpu -> instr_reg));
		cpu -> running = 0;
	}
}

// Execute branch instruction: Bitwise AND instruction's mask and
// cpu's condition code, branch iff result is nonzero.
//
// Echo kind of branch, current cc, whether or not the branch happened,
// and if so, the target of the branch.
//
void instr_BR(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 0) {
        printf("  **ERROR: Doing BR when opcode is not 0.\n");
    }
    else {
        int t=intValueOf(cpu -> instr_reg);
        t=t%4096;
        int mask=t/512;
        int offsetT=t%512;
        int offset;        
        if (offsetT>255){		//take 2's complement
            offset=offsetT-512;
        }
        else{
            offset=offsetT;
        }        
        int branch_condition = mask & cpu -> condition_code;
        Address old_pc = cpu -> pgm_counter;
        if (branch_condition) {
            cpu -> pgm_counter = old_pc + offset;
        }
        char cc;
        if (cpu -> condition_code == 1) {
            cc='P';
        }
        else if (cpu -> condition_code == 2) {
            cc='Z';
        }
        else if (cpu -> condition_code == 4) {
            cc='N';
        }        
        printf("BR    %d, CC = %c, go to x%04hX%s%d = x%04hX\n",offset,cc,old_pc,offset<0?"":"+",offset,cpu -> pgm_counter);
    }
}

void instr_ADD(CPU *cpu) {
    if (op(cpu -> instr_reg) != 1) {
        printf("  **ERROR: Doing ADD when opcode is not 1.\n");
    }
    else {
        int t=intValueOf(cpu -> instr_reg);
        t=t%4096;        
        int dst=t/512;
        int src=(t%512)/64;
        int rest=t%64;
        int flag=rest/32;
        if(flag){
            int srcTemp=rest%32;
            int value=srcTemp>15? srcTemp-32 : srcTemp;
            Word result=value + cpu -> reg[src];
            cpu -> reg[dst] = result;
            char cc;
            if(result==0){
                cpu -> condition_code = 2;
                cc='Z';
            }
            else if(result>0){
                cpu -> condition_code = 1;
                cc='P';
            }
            else if(result<0) {
                cpu -> condition_code = 4;
                cc='N';
            }
            printf("ADD   R%d, R%d, %d;  R%d <- x%04hX + %d = x%04hX; CC = %c\n",dst,src,value,dst,cpu -> reg[src],value,result,cc);
        }
        else {
            int src2 = rest % 8;
            Word result = cpu -> reg[src] + cpu -> reg[src2];
            cpu -> reg[dst] = result;
            char cc;
            if (result == 0) {
                cpu -> condition_code = 2;
                cc='Z';
            }
            else if (result > 0) {
                cpu -> condition_code = 1;
                cc='P';
            }
            else if (result < 0) {
                cpu -> condition_code = 4;
                cc='N';
            }
            printf("ADD   R%d, R%d, R%d;  R%d <- x%04hX & x%04hX = x%04hX; CC = %c\n",dst,src,src2,dst,cpu -> reg[src],cpu -> reg[src2],result,cc);
        }
    }
}

void instr_LD(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 2) {
        printf("  **ERROR: Doing LD when opcode is not 2.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int dst = t / 512;
        int offsetTemp  = t % 512;
        int offset;        
        if (offsetTemp>255) { //take 2's complement
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        Address newLoca = (Address)offset + cpu -> pgm_counter;
        cpu -> reg[dst] = cpu -> memory[newLoca];
        char cc;
        if (cpu -> reg[dst] == 0) {
            cpu -> condition_code = 2;
            cc = 'Z';
        }
        else if (cpu -> reg[dst] > 0) {
            cpu -> condition_code = 1;
            cc = 'P';
        }
        else {
            cpu -> condition_code = 4;
            cc = 'N';
        }
        char *p = offset < 0? "PC" : "PC+";
        printf("LD    R%d, %d; R%d <- M[%s%d] = M[x%04hX] ＝ x%04hX; CC = %c\n",dst,offset,dst,p,offset,newLoca,cpu -> reg[dst],cc);
    }
}

void instr_ST(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 3) {
        printf("  **ERROR: Doing ST when opcode is not 3.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int src = t / 512;
        int offsetTemp  = t % 512;
        int offset;
        
        if (offsetTemp>255) { //take 2's complement
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        
        int newloca = intValueOf(cpu -> pgm_counter + offset);
        cpu -> memory[newloca] = cpu -> reg[src];
        
        printf("ST    R%d, %d;  M[PC%s%d] = M[x%04hX] <- x%04hX\n",src,offset,offset<0 ? "" : "+",offset,(short)newloca,cpu -> reg[src]);
    }
}

void instr_JSR(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 4) {
        printf("  **ERROR: Doing JSR when opcode is not 4.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int flag = t / 2048;
        if (flag) {
            int offsetTemp = t % 2048;
            int offset = offsetTemp > 1023 ? offsetTemp - 2048 : offsetTemp;
            cpu -> reg[7] = cpu -> pgm_counter;
            cpu -> pgm_counter = cpu -> pgm_counter + offset;
            
            printf("JSR   x%04hX%s%d = x%04hX (R7 = x%04hX)\n",cpu -> reg[7],offset<0?"":"+",offset,cpu -> pgm_counter,cpu -> reg[7]);
        }
        else {
            int baseR = (t % 512) / 64;
            Address old_pc = cpu -> pgm_counter;
            Word old_reg = cpu -> reg[baseR];
            cpu -> pgm_counter = old_reg;
            cpu -> reg[baseR] = old_pc;
            printf("JSRR  R%d = x%04hX (R7 = x%04hX)\n",baseR,old_reg,cpu -> reg[baseR]);
        }
    }
}

void instr_AND(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 5) {
        printf("  **ERROR: Doing AND when opcode is not 5.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int dst = t / 512;
        int src = (t % 512) / 64;
        int rest = t % 64;
        int flag = rest /32;
        if (flag) {
            int srcTemp = rest % 32;
            int value = srcTemp > 15? srcTemp-32 : srcTemp;
            Word result = value & cpu -> reg[src];
            cpu -> reg[dst] = result;
            char cc;
            if (result == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (result > 0) {
                cpu -> condition_code = 1;
                cc = 'P';
            }
            else if (result < 0) {
                cpu -> condition_code = 4;
                cc = 'N';
            }
            printf("AND   R%d, R%d, %d;  R%d <- x%04hX & x%04hX = x%x; CC = %c\n",dst,src,value,dst,cpu -> reg[src],(short)value,result,cc);
        }
        else {
            int src2 = rest % 8;
            Word result = cpu -> reg[src] & cpu -> reg[src2];
            cpu -> reg[dst] = result;
            char cc;
            if (result == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (result > 0) {
                cpu -> condition_code = 1;
                cc = 'P';
            }
            else if (result < 0) {
                cpu -> condition_code = 4;
                cc = 'N';
            }
            printf("AND   R%d, R%d, R%d;  R%d <- x%04hX & x%04hX = x%x; CC = %c\n",dst,src,src2,dst,cpu -> reg[src],cpu -> reg[src2],result,cc);
        }
    }
}

void instr_LDR(CPU *cpu) {
    if (op(cpu -> instr_reg)!=6) {
        printf("  **ERROR: Doing LDR when opcode is not 6.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;
        int dst = t / 512;
        int base = (t % 512) / 64;
        int offsetTemp = t % 64;
        int offset = offsetTemp > 31? offsetTemp - 64 : offsetTemp;
        int newloca = intValueOf(cpu -> reg[base]) + offset;
        Word result = cpu ->memory[newloca];
        cpu -> reg[dst] = result;
        char cc;
        if (result == 0) {
            cc = 'Z';
            cpu -> condition_code = 2;
        }
        else if (result > 0) {
            cc = 'P';
            cpu -> condition_code = 1;
        }
        else if (result < 0) {
            cc = 'N';
            cpu -> condition_code = 4;
        }
        printf("LDR   R%d, R%d, %d; R%d <- M[x%04hX%s%d] = M[x%04hX] = x%04hX; CC = %c\n",dst,base,offset,dst,cpu -> reg[base],offset < 0 ? "" : "+",offset,(short)newloca,result,cc);
    }
}

void instr_STR(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 7) {
        printf("  **ERROR: Doing STR when opcode is not 7.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int src = t / 512;
        int base = (t % 512) / 64;
        int offsetTemp = t % 64;
        int offset = offsetTemp > 31 ? offsetTemp - 64 : offsetTemp;
        int newloca = intValueOf(cpu -> reg[base] + offset);
        cpu -> memory[newloca] = cpu -> reg[src];
        
        printf("STR   R%d, R%d, %d;  M[x%04hX%s%d] = M[x%04hX] <- x%04hX\n",src,base,offset,cpu -> reg[base],offset<0?"":"+",offset,(short)newloca,cpu -> reg[src]);
    }
}

void instr_RTI(CPU *cpu) {
    cpu -> running = 0;
    cpu -> condition_code = 1;
    printf("  **ERROR: RTI error and CPU halted\n");
}

void instr_NOT(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 9) {
        printf("  **ERROR: Doing NOT when opcode is not 9.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;        
        int dst = t / 512;
        int src = (t % 512) / 64;
        Word result = ~cpu -> reg[src];
        cpu -> reg[dst] = result;
        char cc;
        if (cpu -> reg[dst] == 0) {
            cpu -> condition_code = 2;
            cc = 'Z';
        }
        else if (cpu -> reg[dst] > 0) {
            cpu -> condition_code = 1;
            cc = 'P';
        }
        else {
            cpu -> condition_code = 4;
            cc = 'N';
        }
        printf("NOT   R%d, R%d;  R%d <- NOT x%04hX = x%04hX; CC = %c\n",dst,src,dst,cpu -> reg[src],result,cc);
    }
}

void instr_LDI(CPU *cpu) {
    if (op(cpu -> instr_reg)!=10) {
        printf("  **ERROR: Doing LDI when opcode is not 10.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;
        int dst = t / 512;
        int offsetTemp = t % 512;
        int offset;
        if (offsetTemp>255) {
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        int newloca = intValueOf(cpu -> pgm_counter + offset);
        int a2 = intValueOf(cpu -> memory[newloca]);
        Word result = cpu -> memory[a2];
        cpu -> reg[dst] = result;
        char cc;
        if (result == 0) {
            cpu -> condition_code = 2;
            cc = 'Z';
        }
        else if (result > 0) {
            cpu -> condition_code = 1;
            cc = 'P';
        }
        else if (result < 0) {
            cpu -> condition_code = 4;
            cc = 'N';
        }
        printf("LDI   R%d, %d;  R%d <- M[M[PC%s%d]] = M[M[x%04hX]]= M[x%04hX]  = x%04hX; CC = %c\n",dst,offset,dst,offset<0? "" : "+",offset,(short)newloca,(short)a2,result,cc);
    }
}

void instr_STI(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 11) {
        printf("  **ERROR: Doing STI when opcode is not 11.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;
        int src = t / 512;
        int offsetTemp  = t % 512;
        int offset;
        if (offsetTemp>255) { //take 2's complement
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        int newloca = intValueOf(cpu -> pgm_counter + offset);
        printf("STI   R%d, %d;  M[M[PC%s%d]] = M[M[x%04hX]]= M[x%04hX] <- x%04hX\n",src,offset,offset < 0? "" : "+",offset,(short)newloca,cpu -> memory[newloca],cpu -> reg[src]);
        cpu -> memory[intValueOf((cpu -> memory[newloca]))] = cpu -> reg[src];
        
    }
}

void instr_JMP(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 12) {
        printf("  **ERROR: Doing JMP when opcode is not 12.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        t = t % 4096;
        int base  = (t % 512) / 64;
        cpu -> pgm_counter = cpu -> reg[base];
        printf("JMP   R%d; go to x%04hX\n",base,cpu -> reg[base]);
    }
}

void instr_err(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 13) {
        printf("  **ERROR: Doing err when opcode is not 13.\n");
    }
    else {
        printf("Error! Congratulations!\n");
    }
}

void instr_LEA(CPU *cpu) {
    if (op(cpu -> instr_reg)!=14) {
        printf("  **ERROR: Doing LEA when opcode is not 14.\n");
    }
    else {
        int t = intValueOf(cpu->instr_reg);
        t = t % 4096;
        int dst = t / 512;
        int offsetTemp = t % 512;
        int offset;
        if (offsetTemp > 255) {
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        unsigned short int result = (Address)offset + cpu -> pgm_counter;
        cpu -> reg[dst] = result;
        char *p = offset < 0 ? "PC" : "PC+";
        char cc;
        if (result == 0) {
            cc = 'Z';
            cpu -> condition_code = 2;
        }
        else if (result > 32767) {
            cc = 'N';
            cpu -> condition_code = 4;
        }
        else {
            cc = 'P';
            cpu -> condition_code = 1;
        }
        printf("LEA   R%d, %d; R%d <- %s%d = x%04hX; CC = %c\n",dst,offset,dst,p,offset,result,cc);
    }
}

void instr_TRAP(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 15) {
        printf("  **ERROR: Doing TRAP when opcode is not 15.\n");
    }
    else {
        int t = intValueOf(cpu -> instr_reg);
        int vector = t % 256;        
        if (vector == 32) {
            char c;
            scanf("%c",&c);
            t = intValueOf(cpu -> reg[0]);
            t = t % 256;
            t = t * 256;
            t = t + c;
            cpu -> reg[0] = t;
            char cc;
            if (t == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (t > 0) {
                cpu -> condition_code = 1;
                cc = 'P';
            }
            else {
                cpu -> condition_code = 4;
                cc = 'N';
            }
            printf("TRAP  x20 GETC; new R0 is %04hX, CC = %c\n",cpu -> reg[0],cc);
        }
        else if (vector == 33) {
            t = cpu -> reg[0];
            int pr = t % 256;
            
            printf("TRAP  x21 OUT: %c\n",pr);
        }
        else if (vector == 34) {
            Address strt = intValueOf(cpu -> reg[0]);
            while (cpu -> memory[strt]) {
                printf("%c",cpu -> memory[strt]);
            }
            printf("\nTRAP  x22 PUTS\n");
        }
        else if (vector == 35) {
            printf("Enter a character\n");
            char c;
            scanf("%c",&c);
            t = intValueOf(cpu -> reg[0]);
            t = t % 256;
            t = t * 256;
            t = t + c;
            cpu -> reg[0] = t;
            char cc;
            if (t == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (t > 0) {
                cpu -> condition_code = 1;
                cc = 'P';
            }
            else {
                cpu -> condition_code = 4;
                cc = 'N';
            }
            printf("TRAP  x23 IN; new R0 is %04hX, CC = %c\n",cpu -> reg[0],cc);
        }
        else if (vector == 36) {
            printf("TRAP  x24 PUTSP; error\n");
        }
        else if (vector == 37) {
            cpu -> running = 0;
            cpu -> condition_code = 1;
            printf("TRAP  x25 Halting; CC = P\n");
        }
    }
}

/* *** STUB *** */


// Read and return a character from standard input.  User must
// enter return after the char.  Just pressing return causes '\n'
// to be returned.  Any extra characters after the first are ignored.
//
int read_char() {
	char buffer[3] = "";
	fgets(buffer, sizeof buffer, stdin);
	return buffer[0];
}

int op(Word w) {
    int t = intValueOf(w);
    int o = t/4096;
    return o;
}

int intValueOf(short a){
    int b = a < 0 ? 65536+a : a;
    return b;
}
/* *** STUB *** */

void dump_control_unit(CPU *cpu) {
    char ctemp;
    if (cpu -> condition_code == 1) {
        ctemp = 'P';
    }
    else if (cpu -> condition_code == 2) {
        ctemp = 'Z';
    }
    else if (cpu -> condition_code == 4) {
        ctemp = 'N';
    }
    printf("  ||PC = x%04hX    IR = x%04hX    CC = %c\n",cpu -> pgm_counter, cpu -> instr_reg, ctemp);
    int i;
    int newLineCounter = 1;
    for (i = 0; i < NBR_REGS; i++) {
        if (newLineCounter == 1) {
            printf("\n");
            newLineCounter = -1;
        }
        newLineCounter++;
        printf("  || R%d x%04hX  % 6d   ",i,cpu -> reg[i],cpu -> reg[i]);
    }
    printf("\n");
}

void dump_memory(CPU *cpu, Address from, Address to) {
    int fm = (int) from;
    int tt = (int) to;
    printf("Memory location from %d to %d\n",fm,tt);
    int i;
    int format = 0;
    for (i = fm; i <= tt; i++) {
        if (format == 0) {
            printf("Memory[x%04hX]: ",(unsigned short)i);
        }
        else if (format == 4) {
            printf(" ");
        }
        printf("% 06d ",cpu -> memory[i]);
        if (format == 7) {
            printf("\n");
            format = 0;
        }
        else {
            format++;
        }
    }
    printf("\n");
}

void helpMsg() {
	printf("Information:\nh or ? for help \nd to dump register and memory\nInput > 0 to execute that many instruction cycles\nreturn to executes one instruction cycle\n\nSDC Instruction set:\n 0xxx: HALT\n 1RMM: Load R <- M[MM]\n 2RMM: Store M[MM] <- R\n 3RMM: Add M[MM] to R\n 4Rxx: Negate R\n 5RMM: Load Immediate R <- MM\n 6RMM: Add Immediate R <- R + MM\n 7xMM: Branch to MM\n 8RMM: Branch Positive to MM if R > 0\n 90xx: Read char into R0\n 91xx: Print char in R0\n 92MM: Print string starting at MM\n 93MM: Dump control unit\n 94MM: Dump memory\n");
}

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
