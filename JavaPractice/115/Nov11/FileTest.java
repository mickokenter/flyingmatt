import java.io.*;
import java.util.Scanner;
public class FileTest {
	public static void main(String[ ] args) 
                             throws IOException {
		Scanner inKey = new Scanner(System.in);						
		System.out.println("Give me a filename: ");
		String fileName = inKey.next();
							 
		File input = new File(fileName);
		Scanner inScan = new Scanner(input);
		
		int i = inScan.nextInt();
		double x = inScan.nextDouble();
		String letter =inScan.next();
		
		System.out.println(""+i +" " + x +" " +letter);	
		
		int[] data = new int[10];
		int index=0;
		while (inScan.hasNext()) {
			if(inScan.hasNextInt()){
				data[index]=inScan.nextInt(); 
				System.out.println(data[index]);
				index++;
			}
			else {
				String garbage=inScan.next();
			}
		}

		for (int j=9; j>=0; j--){
			System.out.println(data[j]);
			}
	}
}