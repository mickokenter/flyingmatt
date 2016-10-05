package XIAQIN;
import XIAQIN.QIU.*;
import java.util.*;
import java.io.*;

public class RodClient{
	static Rod[] rod;
	static double[] dl =null;
	static double[] force =null;
	static int count=0;
	static int greatest=0;
	static double forceGreatest=0;
	
	public static void main(String[] args){
		String str = args[0];
		if(str.equals("textready")){
			try{
				File myFile = new File("coefficients.txt");
				Scanner scan = new Scanner(myFile);
				Scanner scan2 = new Scanner(myFile);
				while(scan.hasNextLine()){
					scan.nextLine();
					count++;
				}
				rod = new Rod[count];
				dl = new double[count];
				force = new double[count];
				for(int i=0; scan2.hasNextLine(); i++){
					String line = scan2.nextLine();
					StringTokenizer st = new StringTokenizer(line,",");
					String mc = st.nextToken();
					String name = st.nextToken();
					String aTemp= st.nextToken();
					String lTemp= st.nextToken();
					String coTemp = st.nextToken();
					String yTemp = st.nextToken();					
					double area=Double.parseDouble(aTemp);
					double length=Double.parseDouble(lTemp);
					rod[i]=new Rod(name,area,length);
					double co =Double.parseDouble(coTemp);
					rod[i].setCo(co);
					double y = Double.parseDouble(yTemp);
					rod[i].setY(y);
					if(mc.equals("AluminumBased")){
						rod[i].setMaterial(MaterialCategories.AluminumBased);
					}
					else if(mc.equals("Metals")){
						rod[i].setMaterial(MaterialCategories.Metals);
					}
					else if(mc.equals("Cellulose")){
						rod[i].setMaterial(MaterialCategories.Cellulose);
					}
					else{
						rod[i].setMaterial(MaterialCategories.Glass);
					}
				}
			}
			catch (IOException ioe){
				System.out.println(ioe.getMessage());
			}
			catch (IllegalArgumentException ia){
				System.out.println(ia.getMessage());
			}
			catch (NullPointerException np){
				System.out.println(np.getMessage());
			}
			for(int i=0;i<rod.length;i++){
				System.out.println("Please enter the temperature change for material: "+rod[i].getName());
				Scanner scan = new Scanner(System.in);
				double dt=scan.nextDouble();
				dl[i]=rod[i].calculateExpansion(rod[i].getCo(),dt);
				force[i] = rod[i].calculateForce(rod[i].getY(),dl[i]);
				if(force[i]>forceGreatest){
					greatest=i;
					forceGreatest=force[i];
				}
				System.out.println("______FIRST OUTPUT REQUIRED______\nThe expansion is: "+dl[i]+"\nThe force is: "+force[i]);
			}
			System.out.println("______SECOND OUTPUT REQUIRED______\nThe greatest force required is: "+forceGreatest+"\nthe rod that requires the largest force is: "+rod[greatest].toString()+"\n\n______THIRD OUTPUT REQUIRED______");
			for(int i=0; i<rod.length;i++){
				System.out.println(rod[i].toString());
			}
		}
		else if(str.equals("notready")){
		System.out.println("I can't provide data unless the text file is ready");
		}
	}
}