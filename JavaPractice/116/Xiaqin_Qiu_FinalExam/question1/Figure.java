package inheritance;

public abstract class Figure{
	int linecolor;
	String fonttype;
	double pencilwidth;
	String name;
	public Figure(){
		linecolor=0;
		fonttype="";
		pencilwidth=0;
		name="";
	}
	public Figure(int l, String f, double p, String n){
		linecolor=l;
		fonttype=f;
		pencilwidth=p;
		name=n;
	}
	
	public void setLinecolor(int l){
		linecolor=l;
	}
	public void setFonttype(String f){
		fonttype=f;
	}
	public void setPencilwidth(double p){
		pencilwidth=p;
	}
	public void setName(String n){
		name=n;
	}
	public int getLinecolor(){
		return linecolor;
	}
	public String getFonttype(){
		return fonttype;
	}
	public double getPencilwidth(){
		return pencilwidth;
	}
	public String getName(){
		return name;
	}
	
	public String toString(){
		String str="the object is: The name is: "+name+" The linecolor is: "+linecolor+" The fonttype is: "+fonttype+" The pencil width is: "+pencilwidth;
		return str;
	}
	
	public boolean equals(Figure f){
		boolean bl;
		if((f.getName().equals(this.getName())) && (f.getLinecolor()==this.getLinecolor()) && (f.getFonttype().equals(this.getFonttype()))){
			bl=true;
		}
		else{
			bl=false;
		}
		return bl;
	}
	
	public abstract double calculateArea();
}