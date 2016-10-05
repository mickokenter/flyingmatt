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
			FileOutputStream file = new FileOutputStream("records.dat");	
			OutputStreamWriter filestream = new OutputStreamWriter(new BufferedOutputStream(file));
			for(int i=0;i<alba.size();i++){
				filestream.write(alba.get(i).getBank_Account_Num()+" "+alba.get(i).getFirst_Name()+" "+alba.get(i).getLast_Name()+" "+alba.get(i).getBalance()+" "+alba.get(i).getFees_Apply()+" "+alba.get(i).getPassword()+"\n");
			}
			filestream.flush();
			file.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
        }
	}
}
