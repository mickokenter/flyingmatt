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
		File myFile = new File(fileName);
		Scanner scan = new Scanner(myFile);
		String str = scan.nextLine();
		int i=0;
		String duplicate="";
		while(scan.hasNextLine()){
			String[] data1 = str.split(" ");
			String dataA= data1[0];
			str= scan.nextLine();
			String[] data2 = str.split(" ");
			String dataB= data2[0];
			i++;
			if(Double.parseDouble(dataA)==Double.parseDouble(dataB)){
				duplicate = duplicate+(i+1)+" "+str+"\n";
			}
		}
		return duplicate;
	}

	public String toString() {
		return "FileName:"+fileName; 
	}
}