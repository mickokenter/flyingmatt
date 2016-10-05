import java.util.*;

public class MyName2 {
	private String first, middle, last;

	public MyName2(String longName){
/*
		int endOfLast = longName.indexOf(',');
		last = longName.substring(0, endOfLast);
		String restOfName =longName.substring(endOfLast+2);
		int blank = restOfName.indexOf(' ');
		if (blank==-1) {   // no middle name (no blank found)
			first = restOfName;
			middle = "";
		}
		else {
			first = restOfName.substring(0, blank);
			middle = restOfName.substring(blank+1);
		}
*/
/*
		String [] data = longName.split(",");
		last = data[0];
		String restOfName = data[1].substring(1);
		String [] data1 = restOfName.split(" ");
		first=data1[0];
		if (data1.length==2) middle=data1[1];
		else middle="";
*/	
		StringTokenizer data = new StringTokenizer(longName,",");
		last = data.nextToken();
		String restOfName = data.nextToken();
		StringTokenizer data1 = new StringTokenizer(restOfName," ");
		first = data1.nextToken();
		if (data1.hasMoreTokens()) middle=data1.nextToken();
		else middle="";
	}

	public String getFirst(){ return first; }

	public String getMiddle(){ return middle; }

	public String getMiddleInitial(){
		if (middle.length()==0) return "";
		else return middle.substring(0,1);
	}

	public String getLast(){ return last; }

	public String toString(){ return first+" "+middle+" "+last;} 

	public static void main(String[] args) {
		MyName2 test1 = new MyName2("Bauer, Matthew John");
		System.out.println("First:"+test1.getFirst()+" Middle:"+test1.getMiddle()+" Last:"+test1.getLast());
		System.out.println(test1);

		MyName2 test2 = new MyName2("Smith, Robert");
		System.out.println("First:"+test2.getFirst()+" Middle:"+test2.getMiddle()+" Last:"+test2.getLast());
		System.out.println(test2);

		MyName2 test3 = new MyName2("Smith, Mary Elizabeth");
		System.out.println("First:"+test3.getFirst()+" MiddleInitial:"+test3.getMiddleInitial()+" Last:"+test3.getLast());
		System.out.println(test3);

		Scanner in = new Scanner(System.in);
		System.out.println("Enter your name in this format 'lastname, firstname middlename'");
		String data=in.nextLine();
		MyName2 test4 = new MyName2(data);
		System.out.println("First:"+test4.getFirst()+" MiddleInitial:"+test4.getMiddleInitial()+" Last:"+test4.getLast());
		System.out.println(test4);
	}
}