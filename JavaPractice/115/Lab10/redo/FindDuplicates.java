import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class FindDuplicates {
	private String fileName;
	private static final String DEFAULT_FILE="input.txt";

	public FindDuplicates(String f) {
		setFileName(f);
	}

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String f) {
		if (f!=null) fileName = f;
		else fileName=DEFAULT_FILE;
	}

	public String getDuplicates() throws IOException {
		File myFile=new File(fileName);
		Scanner scan=new Scanner(myFile);
		int token=0;
		int theLastToken=0;
		String duplicate="";
		String record="";
		int i=0;
		while(scan.hasNextLine()){
			token=scan.nextInt();
			record=scan.nextLine();
			if(i>0){
				if(token==theLastToken){
					duplicate=duplicate+(i+1)+" "+token+record +"\n";
				}
			}
			theLastToken=token;
			i++;
		}
		return duplicate;
	}

	public String toString() {
		return "FileName:"+fileName; 
	}
}