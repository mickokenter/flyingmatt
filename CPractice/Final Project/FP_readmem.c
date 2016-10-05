// CS 350, Spring 2014
//
// Note -- you're welcome to extend this function so
// that it checks for more errors and prints more
// messages (like echoing the name of the file).

// Prompt the user for the name of an LC3 *.hex file
// and read it in.
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
