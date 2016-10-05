import java.util.Scanner;

public class Elimination{
	public static void main(String[] args){
		String input="";
		int a=0;
		int b=0;
		int s=0;
		Dice diceRoll = new Dice();
		Rule play = new Rule(a,b,s,input);
		Scanner scan = new Scanner(System.in);
		while(play.getStatus()){
			diceRoll = new Dice();
			a=diceRoll.geta();
			b=diceRoll.getb();
			s=a+b;
			play.seta(a);
			play.setb(b);
			play.sets(s);
			play.show();
			System.out.println(diceRoll);
			do{
				System.out.print("What is your move (V=values, S=sum, Q=quit)?");
				play.setInput(scan.next());
				play.letsPlay();
			}while(play.getInputStatus());
		}
	}
}