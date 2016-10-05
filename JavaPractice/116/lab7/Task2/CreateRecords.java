package Compiled;
import java.util.*;
import java.io.*;

public class CreateRecords{
	public static void main(String[] args){
		BankAccount ba1=new BankAccount(1234, 'A', 'M', 1000.30, true, "Mypassword");
		BankAccount ba2=new BankAccount(3456, 'G', 'L', 300.34, false, "helenB");
		BankAccount ba3=new BankAccount(7890, 'H', 'J', 1290.0, true, "jwer");
		BankAccount ba4=new BankAccount(6781, 'F', 'D', 260.60, true, "hgfdw");
		ArrayList<BankAccount> alba=new ArrayList<BankAccount>();
		alba.add(ba1);
		alba.add(ba2);
		alba.add(ba3);
		alba.add(ba4);
		try {
			FileOutputStream file = new FileOutputStream("data.txt");	
			OutputStreamWriter filestream = new OutputStreamWriter(new BufferedOutputStream(file));
			for(int i=0;i<alba.size();i++){
				filestream.write(alba.get(i).getBank_Account_Num()+"\t"+alba.get(i).getFirst_Name()+"\t"+alba.get(i).getLast_Name()+"\t"+alba.get(i).getBalance()+"\t"+alba.get(i).getFees_Apply()+"\t"+alba.get(i).getPassword()+"\n");
			}
			filestream.flush();
			file.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
        }
		System.out.println("please indicate if you want the file records.dat read by typing yes");
		Scanner scan=new Scanner(System.in);
		String input=scan.nextLine();
		if(input.equals("yes")){
			try{
				FileInputStream file=new FileInputStream("data.txt");
				BufferedReader filereader=new BufferedReader(new InputStreamReader(file));         
				for(int i=0;i<alba.size();i++) {
					String text=filereader.readLine();
					System.out.println(text);
				}
				
				file.close();
				filereader.close();
			}
			catch(IOException ioe){
				System.out.println(ioe.toString()); 
			}
		}
		else{
			System.exit(0);
		}
	}
}
