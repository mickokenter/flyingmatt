import java.util.*;

public class TwoDimMin{
	public static void main(String[] args){
		int [][] numberArray = {{9,8,7,6},{10,20,30,40}};
		int min=numberArray[0][0];
		Vector v=new Vector();
		for(int i=0; i<numberArray.length; i++){
			for(int j=0; j<numberArray[i].length; j++){
				System.out.println(numberArray[i][j]);
				if(numberArray[i][j]<min){
						min=numberArray[i][j];
				}
				v.add(numberArray[i][j]);
			}
		}
		System.out.println("The minimum value in th array is: "+min);
		for(int i=0; i<v.size(); i++){
			System.out.println("The value in vector is: "+v.elementAt(i));
		}
	}
}