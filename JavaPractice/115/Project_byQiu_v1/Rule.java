import java.util.Scanner;

public class Rule{
	int a;
	int b;
	int s=0;
	int score=0;
	int [] tiles = new int[12];
	String [] sign = new String[12];
	boolean status = true;
	boolean inputStatus = false;
	String input;
	Scanner scan = new Scanner(System.in);
	public Rule(){
		initiate();
		letsPlay();
	}
	public void letsPlay(){
		while(status){
			Dice diceRoll = new Dice();
			a=diceRoll.a();
			b=diceRoll.b();
			show();
			System.out.println("Dice roll: " + a + " " + b);
			do{
				System.out.print("What is your move (V=values, S=sum, Q=quit)?");
				input=scan.next();
				if(input.equals("v")||input.equals("V")){
					if(a==b){
						tryAgain();
					}
					else {
						if(mark(a) & mark(b)){
							sign[a-1]="X";
							sign[b-1]="X";
							inputStatus=false;
						}
						else {
							tryAgain();
						}
					}
				}
				else if(input.equals("s")||input.equals("S")){
					s=a+b;
					if(mark(s)){
						sign[s-1]="X";
						inputStatus=false;
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
			} while(inputStatus);
		}
	}
	public void initiate(){
		for(int i=0;i<12;i++){
			tiles[i]=i+1;
			sign[i]=".";
		}
	}
	public void quit(){	
		status = false;
		inputStatus = false;
		for(int i=0;i<12;i++){
			if(sign[i].equals(".")){
				score=score+tiles[i];
			}
		}
		show();
		System.out.println("Score = " + score);
	}
	public void tryAgain(){
		System.out.println("Try again.");
		inputStatus=true;
	}
	public boolean mark(int m){
		boolean bl=true;
		for(int i=0;i<12;i++){
			if(m==tiles[i]){
				if(sign[i].equals(".")){
					bl = true;
				}
				else bl = false;
			}
		}
		return bl;
	}
	public void show(){
		System.out.print("\n");
		for(int i=0;i<12;i++){
			System.out.print(tiles[i]+"\t");
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