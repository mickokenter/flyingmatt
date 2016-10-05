import java.text.NumberFormat;
public class Test {
	  public static void main(String[] args){
		private static NumberFormat 
		        myFormatter = NumberFormat.getCurrencyInstance();
		
		System.out.println(myFormatter.format(5));
		System.out.println(myFormatter.format(5.666666));
		System.out.println(myFormatter.format(5555555.666666));	
		
	}	
	/*
	public String toString(){
		return "Taxable Income: " +  myFormatter.format(income) +
				" Tax Paid: " + myFormatter.format(taxPaid());
	}				
*/
}		