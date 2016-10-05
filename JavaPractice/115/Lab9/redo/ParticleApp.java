import java.util.Scanner;
public class ParticleApp {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double a, b, c, d, start, end, increment;

		System.out.print("Enter the values for a, b, c, d: ");
		a = input.nextDouble();
		b = input.nextDouble();
		c = input.nextDouble();
		d = input.nextDouble();

		Particle particle1 = new Particle(a, b, c, d);
		System.out.println("Particle Object Created");
		System.out.println(particle1.toString()+ "\n");

		System.out.print("Enter the start time: ");
		start = input.nextDouble();
		System.out.print("Enter the end time: ");
		end = input.nextDouble();
		System.out.print("Enter the increment: ");
		increment = input.nextDouble();
		particle1.table(start, end, increment);

		// ADD MORE TESTING

	}
}