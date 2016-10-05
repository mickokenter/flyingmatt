
//Spring 2011
//George Koutsogiannakis
//EXAMPLE OF WRITING INTO A TEXT FILE (TEXT TYPE OF DATA)
// IN THIS EXAMPLE THE USER ENTERS DATA FROM TEH KEYBOARD AND THE 
//DATA IS WRITTEN INTO A FILE AS STRINGS
//THE PROGRAM PROVIDES THE CHOIC ETO HAVE TEH DATA READ FROM TEH FILE ALSO
import java.io.*;
import java.util.Scanner;
public class  WriteStrings 
{
	public static void main(String[] args) 
	{
		OutputStreamWriter filestream;
		FileOutputStream file;
		String filename=" ";
		
		try 
		{
					//OPEN STANDARD INPUT STREAM TO READ KEYBOARD
					//Read one line at a time as type dby the user
					Scanner scan=new Scanner(System.in);
					//Use the first lene typesd a steh name of the file to be created
					System.out.println("please type the name of the file that you want to write into (including the .txt extansion)");
					filename=scan.nextLine();
					file = new FileOutputStream(filename, true);
					//true in the constructor means that if the file exists, append the new data in teh file.
					filestream= new OutputStreamWriter(new BufferedOutputStream(file));


					System.out.println("Please continue with the information that you want to write in the file");
					String str=" ";
					str=scan.nextLine();
					while(!str.equals("-1"))
					{
						
						//WRITE THE STRING LINE INTO A FILE
						
					
						
						filestream.write(str);
						//WRITE A NEW LINE CHARACTER SO THAT THE NEXT DATA IS WRITTEN IN THE NEXT LINE
						filestream.write("\n");
						
						System.out.println("If more data, type next line otherwise type -1");
						str=scan.nextLine();
						
					}
					//END OD DATA FLUSH OUT  THE BUFFER MEMMORY INTO TEH FILE AND CLOSE TEH STREAM 
					filestream.flush();
					file.close();
		
		}
		catch(IOException ioe)
		{
			System.out.println("Something is wrong");
		}

		//CODE TO READ THE FILE FOLLOWS
		
		Scanner scan1=new Scanner(System.in);
		System.out.println("Type read if you want the data from the file created to be read");
		String str1=scan1.nextLine();
		System.out.println("The file to be read is:"+" "+filename);
		
		
		
		
		if(str1.equals("read"))
		{
			try{
				FileInputStream file1=new FileInputStream(filename);
				BufferedReader filereader1=new BufferedReader(new InputStreamReader(file1));         
				int  index=0;
				int count=0;
				
				String reading=" ";
				
				
				while((reading=filereader1.readLine())!=null) 
				{
					
				 
					System.out.println(reading);
	
					
					
				}
				
				file1.close();
				filereader1.close();
			}//   end of try
			catch(FileNotFoundException fnf)
			{
				System.out.println("The file was not found");
			}
			catch(IOException ioe)
			{
					System.out.println(ioe.toString()); 
			}
			
		}//end of if
		else
		    System.out.println("The program will exit without reading the file");

	}
}
