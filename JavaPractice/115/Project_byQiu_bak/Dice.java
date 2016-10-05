import java.util.Random;

public class Dice{
	public Dice(){
		a();
		b();
	}
	Random rand = new Random();
	public int a(){
		return rand.nextInt(6)+1;
	}
	public int b(){
		return rand.nextInt(6)+1;
	}
}