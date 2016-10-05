//George Koutsogiannakis
//Spring 2010
import java.io.*;
import java.util.*;
public class TestExceptionsTryRequired
{
	public static void main(String[] args) 
	{
		File myFile=new File("TextDoc.txt");
		Scanner scan=new Scanner(myFile);

	}
}
/* This file will not compile: 
A try/catch is needed:
Compiler Message:
---------- Compiler ----------
TestExceptionsTryRequired.java:10: unreported exception java.io.FileNotFoundException; must be caught or declared to be thrown
		Scanner scan=new Scanner(myFile);
		             ^
1 error
Normal Termination
Output completed (0 sec consumed).
This is a checked Exception type.


API FOR SCANNER____________________

Scanner

public Scanner(File source,
               String charsetName)
        throws FileNotFoundException

    Constructs a new Scanner that produces values scanned from the specified file. Bytes from the file are converted into characters using the specified charset.

    Parameters:
        source - A file to be scanned
        charsetName - The encoding type used to convert bytes from the file into characters to be scanned 
    Throws:
        FileNotFoundException - if source is not found 
        IllegalArgumentException - if the specified encoding is not found




*/
