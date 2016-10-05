public class Date { 
	private int month;
	private int day;
	private int year;
	/** constructors */
	public Date() {
    	setDate( 1, 1, 2000 );
		}
	public Date( int mm, int dd, int yyyy ) {
		setDate( mm, dd, yyyy );
		}
	/* accessor methods */
	int getMonth( ) { return month; }
	int getDay( )   { return day; }
	int getYear( )  { return year; }

	/** mutator methods */
	private void setMonth( int mm )  {
		if (mm>= 1 && mm <= 12)
		month=mm;
		else month=1;
		}
	private void setDay( int dd ) {
		if (dd>= 1 && dd <= 31)
		day=dd;
	else day=1;
		}
	private void setYear( int yyyy ) {
    	year = yyyy;
		}
	public void setDate( int mm, int dd, int yyyy )  {
    	setYear(yyyy);
    	setMonth( mm );
    	setDay( dd );
		}
	public String toString( ) {
		return month + "/" + day + "/" + year;
		}
   
	public Date(int mm,int yyyy){
    	setDate( mm, 1, yyyy );
		}
	public static boolean setyear(int yyyy){
		if(yyyy%400==0) return true;
    	else if (yyyy%100==0) return false;
		else if (yyyy%4==0) return true;
		else return false;
		}
	public int daysInMonth(){
		if(month==2){
		if(setyear(year)){return 29;}
		else {return 28;}
		}
		else if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
		return 31;
		}
		else { return 30;}
		}
}