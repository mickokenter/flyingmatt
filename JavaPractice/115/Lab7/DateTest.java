public class DateTest {
	public static void main(String[] args) {
	Date xmas = new Date(12, 25, 2014 );
	System.out.println( "Christmas Day this year is " + xmas);
	Date bday = new Date(10, 21, 1964 );
	System.out.println(bday);
    System.out.println(bday.getMonth());	 
	bday.setDate(bday.getMonth(), bday.getDay(), 1965);
	System.out.println(bday);
	Date newdate1 = new Date(7,2013);
	System.out.println(newdate1);
	Date newdate2 = new Date(2,2012);
	System.out.println(newdate2);
	
	System.out.println(newdate1.setyear(2013));
	System.out.println(newdate2.setyear(2012));
	System.out.println(newdate1.setyear(1000));
	System.out.println(newdate2.setyear(2000));
	System.out.println(bday.daysInMonth());
	}
}