import java.util.Random;

public class Dice{
	int a;
	int b;
	public Dice(){
		seta();
		setb();
	}
	Random rand = new Random();
	public void seta(){
		a = rand.nextInt(6)+1;
	}
	public void setb(){
		b = rand.nextInt(6)+1;
	}
	public int geta(){
		return a;
	}
	public int getb(){
		return b;
	}
	public String toString(){
		return "Dice roll: " + a + " " + b;
	}
}