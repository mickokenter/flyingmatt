import java.util.Scanner;

public class Temperature{
	public static void main(String[] args){
		System.out.println("How many days worth of high temperatures do you have to enter? ");
		Scanner scan = new Scanner(System.in);
		int days=0;
		int input=scan.nextInt();
		if(input>0){
			days = input;
		}
		double[] temp = new double[days];
		double change = 0;
		int day1=0;
		int day2=1;
		for(int i=0; i<days; i++){
			System.out.println("Enter high temperature for day #" + (i+1) + ":");
			temp[i]=scan.nextDouble();		
			if(i>0){
				if(Math.abs(temp[i]-temp[i-1])>Math.abs(change)){
					change=temp[i]-temp[i-1];
					day1=i;
					day2=i+1;
				}
			}
		}
		if(input>0 && days>1){
			System.out.println("The maximum day to day high temperature change is " + change + " and occurred between days " + day1 + " and "+day2);
		}
		else if(days==1){
			System.out.println("Only ONE day's temperature input. NO maximum change.");
		}
		else {
			System.out.println("Your days input cannot be recognized. Please run the program again and input a POSITIVE integer.");
		}
	}
}