import java.io.IOException;
import java.util.Scanner;

public class FindDuplicatesApp {
	public static void main(String[] args) throws IOException {
		String fileName;
		Scanner scan = new Scanner( System.in );
		FindDuplicates a;

		System.out.print( "Enter File Name: ");
		fileName=scan.next();
		a = new FindDuplicates(fileName);
		System.out.println(a);
		System.out.println("DUPLICATES\n" + a.getDuplicates());
	}
}