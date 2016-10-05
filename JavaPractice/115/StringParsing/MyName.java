import java.util.Scanner;

public class MyName {
	private String first, middle, last;

	public MyName(String longName){
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
		MyName test1 = new MyName("Bauer, Matthew John");
		System.out.println("First:"+test1.getFirst()+"Middle:"+test1.getMiddle()+"Last:"+test1.getLast());
		System.out.println(test1);

		MyName test2 = new MyName("Smith, Robert");
		System.out.println("First:"+test2.getFirst()+"Middle:"+test2.getMiddle()+"Last:"+test2.getLast());
		System.out.println(test2);

		MyName test3 = new MyName("Smith, Mary Elizabeth");
		System.out.println("First:"+test3.getFirst()+"MiddleInitial:"+test3.getMiddleInitial()+"Last:"+test3.getLast());
		System.out.println(test3);

		Scanner in = new Scanner(System.in);
		System.out.println("Enter your name in this format 'lastname, firstname middlename'");
		String data=in.nextLine();
		MyName test4 = new MyName(data);
		System.out.println("First:"+test4.getFirst()+"MiddleInitial:"+test4.getMiddleInitial()+"Last:"+test4.getLast());
		System.out.println(test4);
	}
}