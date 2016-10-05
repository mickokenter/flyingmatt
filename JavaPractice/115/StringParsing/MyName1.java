import java.util.Scanner;

public class MyName1 {
	private String first, middle, last;

	public MyName1(String longName){
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
		String [] data = longName.split(",");
		last = data[0];
		String restOfName = data[1].substring(1);
		String [] data1 = restOfName.split(" ");
		first=data1[0];
		if (data1.length==2) middle=data1[1];
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
		MyName1 test1 = new MyName1("Bauer, Matthew John");
		System.out.println("First:"+test1.getFirst()+" Middle:"+test1.getMiddle()+" Last:"+test1.getLast());
		System.out.println(test1);

		MyName1 test2 = new MyName1("Smith, Robert");
		System.out.println("First:"+test2.getFirst()+" Middle:"+test2.getMiddle()+" Last:"+test2.getLast());
		System.out.println(test2);

		MyName1 test3 = new MyName1("Smith, Mary Elizabeth");
		System.out.println("First:"+test3.getFirst()+" MiddleInitial:"+test3.getMiddleInitial()+" Last:"+test3.getLast());
		System.out.println(test3);

		Scanner in = new Scanner(System.in);
		System.out.println("Enter your name in this format 'lastname, firstname middlename'");
		String data=in.nextLine();
		MyName1 test4 = new MyName1(data);
		System.out.println("First:"+test4.getFirst()+" MiddleInitial:"+test4.getMiddleInitial()+" Last:"+test4.getLast());
		System.out.println(test4);
	}
}