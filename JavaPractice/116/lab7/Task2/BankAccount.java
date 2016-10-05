package Compiled;

public class BankAccount{
	int Bank_Account_Num=0;
	char First_Name=' ';
	char Last_Name=' ';
	double Balance=0.;
	boolean Fees_Apply=false;
	String Password=" ";

	public BankAccount(){
		Bank_Account_Num=0;
		First_Name=' ';
		Last_Name='B';
		Balance=0;
		Fees_Apply=false;
		Password="";
	}

	public BankAccount(int ban, char fn, char ln, double b, boolean fa, String p){
		Bank_Account_Num=ban;
		First_Name=fn;
		Last_Name=ln;
		Balance=b;
		Fees_Apply=fa;
		Password=p;
	}

	public void setBank_Account_Num(int ban){
		Bank_Account_Num=ban;
	}
	public void setFirst_Name(char fn){
		First_Name=fn;
	}
	public void setLast_Name(char ln){
		Last_Name=ln;
	}
	public void setBalance(double b){
		Balance=b;
	}
	public void setFees_Apply(boolean fa){
		Fees_Apply=fa;
	}
	public void setPassword(String p){
		Password=p;
	}
	public int getBank_Account_Num(){
		return Bank_Account_Num;
	}
	public char getFirst_Name(){
		return First_Name;
	}
	public char getLast_Name(){
		return Last_Name;
	}
	public double getBalance(){
		return Balance;
	}
	public boolean getFees_Apply(){
		return Fees_Apply;
	}
	public String getPassword(){
		return Password;
	}
	
	public String toString(){
		String str="Bank_Account_Num is: "+Bank_Account_Num+" First_Name is: "+First_Name+" Last_Name is: "+Last_Name+" Balance is: "+Balance+" Fees_Apply is: "+Fees_Apply+" Password is: "+Password;
		return str;
	}
}
