public class Rule{
	int a;
	int b;
	int s=0;
	int score=0;
	String [] sign = new String[12];
	String input;
	boolean status = true;
	boolean inputStatus = false;
	public Rule(int a, int b, int s, String input){
		initiate();
		seta(a);
		setb(b);
		sets(s);
		setInput(input);
	}
	public void letsPlay(){
		if(input.equals("v")||input.equals("V")){
			if(a==b){
				tryAgain();
			}
			else {
				if(mark(a) & mark(b)){
					sign[a-1]="X";
					sign[b-1]="X";
					setInputStatus(false);
				}
				else {
					tryAgain();
				}
			}
		}
		else if(input.equals("s")||input.equals("S")){
			if(mark(s)){
				sign[s-1]="X";
				setInputStatus(false);
			}
			else {
				tryAgain();
			}
		}
		else if(input.equals("q")||input.equals("Q")){
			quit();
		}
		else {
			System.out.println("!!!Your input CANNOT be recognized.!!!"+"\n"+"Please input a VALID character.");
			tryAgain();
		}
	}
	public void initiate(){
		for(int i=0;i<12;i++){
			sign[i]=".";
		}
	}
	
	//mutators
	public void seta(int inputa){a=inputa;}
	public void setb(int inputb){b=inputb;}
	public void sets(int inputs){s=inputs;}
	public void setInput(String inputInput){input=inputInput;}
	public void setInputStatus(boolean bln){inputStatus = bln;}
	public void setStatus(boolean bln){status = bln;}
	//accessors
	public boolean getInputStatus(){return inputStatus;}
	public boolean getStatus(){return status;}
	
	public void quit(){	
		setStatus(false);
		setInputStatus(false);
		for(int i=0;i<12;i++){
			if(sign[i].equals(".")){
				score=score+(i+1);
			}
		}
		show();
		System.out.print("Score = " + score +"\n");
	}
	public void tryAgain(){
		System.out.println("Try again.");
		setInputStatus(true);
	}
	public boolean mark(int m){
		boolean bl=true;
		if(sign[m-1].equals(".")){
			bl = true;
		}
		else bl = false;
		return bl;
	}
	public void show(){
		System.out.print("\n");
		for(int i=0;i<12;i++){
			System.out.print((i+1)+"\t");
		}
		System.out.print("\n");
		for(int i=0;i<12;i++){
			System.out.print(sign[i]+"\t");
		}
		System.out.print("\n");
	}
	public String toString(){
		return "Score = " + score +"\n";
	}
}