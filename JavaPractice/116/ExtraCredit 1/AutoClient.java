public class AutoClient{
	public static void main(String[] args){
		Auto auto1=new Auto(2012, "Ford", 202.3);
		Auto auto2=new Auto(2013, "BMW", 123.5);
		Auto auto3=new Auto(2014, "Ford", 202.3);
		
		System.out.println(auto1);
		System.out.println(auto2);
		System.out.println(auto3);
		
		if(auto1.equals(auto2)){
			System.out.println("a1 and a2 are equal");
		}
		else System.out.println("a1 and a2 are NOT equal");
		if(auto1.equals(auto3)){
			System.out.println("a1 and a3 are equal");
		}
		else System.out.println("a1 and a3 are NOT equal");
		
	}
}