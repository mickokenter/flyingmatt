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
    void helpMessage();
    void readFromFile(CPU *cpu);

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
	int  read_char();
    int  intValueOf(short a);
    // Read a character for GETC, IN
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
    
    printf("\nBeginning execution; type h for help\n");
    char input[16];
    int r = 1;
    while (r) {
        printf("> ");
        fgets(input, sizeof input, stdin);
        int c = input[0];
        if (c == 104 /*command h*/) {
            helpMessage();
        }
        else if (c == 113/*command q*/) {
            cpu -> running = 0;
            printf("halted\n");
            r = 0;
        }
        else if (c == 100/*command d*/) {
            dump_control_unit(cpu);
        }
        else if (c >47 && c<58 /*numbers*/) {
            if (cpu -> running == 0) {
                printf("Halted, CPU not running\n");
            }
            else {
                int i;
                int times=0;//number of instruction_cycle needing excuting
                for (i = 0; i<strlen(input)-1/*<cr> has int value of 38. length of the string counts until <cr>*/; i++) {
                    times=times*10+input[i]-'0';
                }//convert input string to number
                for (i = 0; i<times; i++) {
                    instruction_cycle(cpu);
                }
            }
        }
        else if(c == 109/*command m*/) {
            unsigned int nn1, nn2;
            sscanf(input,"m x%x x%x",&nn1,&nn2);
            int n1 = (int)nn1;
            int n2 = (int)nn2;
            if (n1<0 || n2 > MEMLEN-1) {
                printf("ERROR: n1 or n2 is illegal.\n");
            }
            else {
                dump_memory(cpu, (Address)n1, (Address)n2);
            }
        }
        else if(c == 10/*??ASCII for carriage return <cr>*/){
            if (cpu -> running == 0) {
                printf("Halted, CPU not running\n");
            }
            else {
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
    
    //readMemory(); from text
    readFromFile(cpu);
	// *** STUB ***
    
    
}

void dump_control_unit(CPU *cpu) {
    char cc;
    if (cpu -> condition_code == 1) {
        cc = 'P';
    }
    else if (cpu -> condition_code == 2) {
        cc = 'Z';
    }
    else if (cpu -> condition_code == 4) {
        cc = 'N';
    }
    printf("  ||PC = x%04hX    IR = x%04hX    CC = %c\n",cpu -> pgm_counter, cpu -> instr_reg,cc);
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
    int n1 = (int) from;
    int n2 = (int) to;
    printf("Display memory location from %d to %d\n",n1,n2);
    int i;
    int format = 0;
    for (i = n1; i <= n2; i++) {
        if (format == 0) {
            printf("Memory[x%04hX]: ",(unsigned short)i);
        }
        else if (format == 4) {
            printf("  ");
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
        case  0: instr_BR  (cpu); break;
        case  1: instr_ADD (cpu); break;
        case  2: instr_LD  (cpu); break;
        case  3: instr_ST  (cpu); break;
        case  4: instr_JSR (cpu); break;
        case  5: instr_AND (cpu); break;
        case  6: instr_LDR (cpu); break;
        case  7: instr_STR (cpu); break;
        case  8: instr_RTI (cpu); break;
        case  9: instr_NOT (cpu); break;
        case 10: instr_LDI (cpu); break;
        case 11: instr_STI (cpu); break;
        case 12: instr_JMP (cpu); break;
        case 13: instr_err (cpu); break;
        case 14: instr_LEA (cpu); break;
        case 15: instr_TRAP(cpu); break;
	/* *** STUB *** */

        default:
            printf("Bad opcode: %d; quitting\n", op(cpu -> instr_reg));
            cpu -> running = 0;
	}
}

void helpMessage () {
    printf("  Help: Summary of Commands\n    h        -> Print a summary of the simulator commands\n    d        -> Dump control unit\n    m n1 n2  -> Display memory at locations from n1 to n2\n    Integer  -> Run that number of instruction cycles\n    No input -> Run one instruction cycle\n    q        -> quit\n");
}

void readFromFile (CPU *cpu) {
    //open the file
    FILE *f;
    char *mode = "r";
    char filename[100];
    printf("Please enter the input file\n> ");
    scanf("%s",filename);
    getchar();
    f = fopen(filename,mode);
    while (f == NULL) {
        printf("The file doesn't exist. Please enter another file\n> ");
        scanf("%s",filename);
        f = fopen(filename,mode);
    }
    //file is opened
    
    //store instructions into the memory array
    //1. before ORIG. everything is zero
    unsigned int instr;
    fscanf(f,"%x",&instr);
    cpu -> pgm_counter = instr;
    int strt = (int)instr;
    int i;
    for (i = 0; i < strt; i++) {
        cpu -> memory[i] = (Word)0;
    }
    
    //2. store existing data, tail can't reach 65536-1
    while (!feof(f)) {
        if (strt < 65536-1) {
            fscanf(f,"%x",&instr);
            int in = (int)instr;
            Word inn = (Word) in;
            cpu -> memory[strt] = inn;
            printf("x%04hX: x%04hX  %6d\n",(unsigned short int)strt,inn,inn);
            strt++;
        }
        else {
            printf("  Overflow while reading file\n");
        }
    }
    
    //3. if there's more memory left, set them to zeros. last one is halt
    for (i = strt+1; i < 65536-1 ; i++) {
        cpu -> memory[i] = (Word)0;
    }
    
    //4. last one is halt.
    cpu -> memory[65535] = 65535;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int mask = temp / 512;
        int offsetTemp  = temp % 512;
        int offset;
        
        //convert offset to 2's compliment value
        if (offsetTemp>255) {
            offset = offsetTemp - 512;
        }
        else {
            offset = offsetTemp;
        }
        
        int branch_condition = mask & cpu -> condition_code;
        Address old_pc = cpu -> pgm_counter;
        if (branch_condition) {
            cpu -> pgm_counter = old_pc + offset;
        }
        char cc;
        if (cpu -> condition_code == 1) {
            cc = 'P';
        }
        else if (cpu -> condition_code == 2) {
            cc = 'Z';
        }
        else if (cpu -> condition_code == 4) {
            cc = 'N';
        }
        
        printf("BR    %d, CC = %c, go to x%04hX%s%d = x%04hX\n",offset,cc,old_pc,offset<0?"":"+",offset,cpu -> pgm_counter);
    }
}

void instr_ADD(CPU *cpu) {
    if (op(cpu -> instr_reg) != 1) {
        printf("  **ERROR: Doing ADD when opcode is not 1.\n");
    }
    else {
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int dst = temp / 512;
        int src = (temp % 512) / 64;
        int rest = temp % 64;
        int flag = rest /32;
        if (flag) {
            int srcTemp = rest % 32;
            int value = srcTemp > 15? srcTemp-32 : srcTemp;
            Word result = value + cpu -> reg[src];
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
            
            printf("ADD   R%d, R%d, %d;  R%d <- x%04hX + %d = x%04hX; CC = %c\n",dst,src,value,dst,cpu -> reg[src],value,result,cc);
        }
        else {
            int src2 = rest % 8;
            Word result = cpu -> reg[src] + cpu -> reg[src2];
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
            printf("ADD   R%d, R%d, R%d;  R%d <- x%04hX & x%04hX = x%04hX; CC = %c\n",dst,src,src2,dst,cpu -> reg[src],cpu -> reg[src2],result,cc);
        }
    }
}

void instr_LD(CPU *cpu) {
    if (op(cpu -> instr_reg)!= 2) {
        printf("  **ERROR: Doing LD when opcode is not 2.\n");
    }
    else {
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int dst = temp / 512;
        int offsetTemp  = temp % 512;
        int offset;
        
        //convert offset to 2's compliment value
        if (offsetTemp>255) {
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
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int src = temp / 512;
        int offsetTemp  = temp % 512;
        int offset;
        
        //convert offset to 2's compliment value
        if (offsetTemp>255) {
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int flag = temp / 2048;
        if (flag) {
            int offsetTemp = temp % 2048;
            int offset = offsetTemp > 1023 ? offsetTemp - 2048 : offsetTemp;
            cpu -> reg[7] = cpu -> pgm_counter;
            cpu -> pgm_counter = cpu -> pgm_counter + offset;
            
            printf("JSR   x%04hX%s%d = x%04hX (R7 = x%04hX)\n",cpu -> reg[7],offset<0?"":"+",offset,cpu -> pgm_counter,cpu -> reg[7]);
        }
        else {
            int baseR = (temp % 512) / 64;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int dst = temp / 512;
        int src = (temp % 512) / 64;
        int rest = temp % 64;
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
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        int dst = temp / 512;
        int base = (temp % 512) / 64;
        int offsetTemp = temp % 64;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int src = temp / 512;
        int base = (temp % 512) / 64;
        int offsetTemp = temp % 64;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int dst = temp / 512;
        int src = (temp % 512) / 64;
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
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        int dst = temp / 512;
        int offsetTemp = temp % 512;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int src = temp / 512;
        int offsetTemp  = temp % 512;
        int offset;
        
        //convert offset to 2's compliment value
        if (offsetTemp>255) {
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        temp = temp % 4096;
        
        int base  = (temp % 512) / 64;
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
        int temp = intValueOf(cpu->instr_reg);
        temp = temp % 4096;
        int dst = temp / 512;
        int offsetTemp = temp % 512;
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
        //find the following 12 digits;
        int temp = intValueOf(cpu -> instr_reg);
        int vector = temp % 256;
        
        if (vector == 32) {//GETC
            char c;
            scanf("%c",&c);
            temp = intValueOf(cpu -> reg[0]);
            temp = temp % 256;
            temp = temp * 256;
            temp = temp + c;
            cpu -> reg[0] = temp;
            char cc;
            if (temp == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (temp > 0) {
                cpu -> condition_code = 1;
                cc = 'P';
            }
            else {
                cpu -> condition_code = 4;
                cc = 'N';
            }
            printf("TRAP  x20 GETC; new R0 is %04hX, CC = %c\n",cpu -> reg[0],cc);
        }
        else if (vector == 33) {//OUT
            temp = cpu -> reg[0];
            int pr = temp % 256;
            
            printf("TRAP  x21 OUT: %c\n",pr);
        }
        else if (vector == 34) {//PUTS
            Address strt = intValueOf(cpu -> reg[0]);
            while (cpu -> memory[strt]) {
                printf("%c",cpu -> memory[strt]);
            }
            printf("\nTRAP  x22 PUTS\n");
        }
        else if (vector == 35) {//IN
            printf("Enter a character\n");
            char c;
            scanf("%c",&c);
            temp = intValueOf(cpu -> reg[0]);
            temp = temp % 256;
            temp = temp * 256;
            temp = temp + c;
            cpu -> reg[0] = temp;
            char cc;
            if (temp == 0) {
                cpu -> condition_code = 2;
                cc = 'Z';
            }
            else if (temp > 0) {
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
        else if (vector == 37) {//HALT
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
    int temp = intValueOf(w);
    int o = temp/4096;
    return o;
}

int intValueOf(short a){
    int b = a < 0 ? 65536+a : a;
    return b;
}
/* *** STUB *** */

