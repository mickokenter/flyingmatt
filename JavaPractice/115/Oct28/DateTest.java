public class DateTest {
	public static void main(String[] args) {
	 System.out.println(Date.getCount());
	 Date xmas = new Date(12, 25, 2014 );
     System.out.println( "Christmas Day this year is " + xmas);
	 System.out.println(Date.getCount());
     Date bday = new Date(10, 21, 1964 );
	 System.out.println(Date.getCount()); 
     System.out.println(bday);
    // System.out.println(bday.getMonth());
	int myMonth=bday.getMonth();	 
	 bday.setDate(bday.getMonth(), bday.getDay(), 1965);
	 System.out.println(bday.toString());
	 System.out.println(bday.equals(xmas));
	 System.out.println(xmas.equals(xmas));
	 Date xmas2= xmas;
	 System.out.println(xmas2.toString());
	 xmas.setDate(xmas.getMonth(), xmas.getDay(), 2000);
	 System.out.println(xmas2);	 
	 System.out.println(xmas2.equals(bday));	
	 System.out.println(xmas2.compareTo(bday));	
	 System.out.println(bday.compareTo(bday));	
	 System.out.println(bday.compareTo(xmas2));	
	}
}
