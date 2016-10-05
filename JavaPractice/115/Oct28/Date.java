public class Date { 
  private int month;
  private int day;
  private int year;
  
  private static int count=0;
  public static int getCount(){
	return count;
  }
	  
/** constructors */
  public Date() {
    setDate( 1, 1, 2000 );
	count++;
  }
  public Date( int mm, int dd, int yyyy ) {
    setDate( mm, dd, yyyy );
	count=count+1;
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
  public boolean equals(Date thatDate){
	if(month==thatDate.month &&
		year==thatDate.year &&
		day==thatDate.day) return true;
	else return false;
  }
  
   public int compareTo(Date thatDate){
		int returnvalue=0;
		if(year>thatDate.year) returnvalue=1;
		else if (year<thatDate.year) returnvalue=-1;
		else { 	// years equal
			if(month>thatDate.month)returnvalue=1;
			else if (month<thatDate.month) returnvalue=-1;		
			else {	// year and month equal
				if(day>thatDate.day) returnvalue=1;
				else if (day<thatDate.day) returnvalue=-1;
				else returnvalue=0;
			}
		}
		return returnvalue;
	}
  }