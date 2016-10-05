public abstract class Figure implements INT1,INT2{
	String nameOfShape="";
	public Figure(){
		nameOfShape="";
	}
	public Figure(String nos){
		nameOfShape=nos;
	}
	public ArrayList<String> costToDraw(Customer c, int s);
	public String toString(){
		String str="The name of the shape is: "+nameOfShape;
		return str;
	}
}