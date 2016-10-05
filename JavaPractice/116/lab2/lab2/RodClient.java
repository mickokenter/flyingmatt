package XIAQIN;
import XIAQIN.QIU.*;
import java.util.*;
import java.io.*;

public class RodClient{
	static Rod[] rod;
	static double[] data=null;
	static double[] data1=null;
	static double[] temp=null;
	static double[] dl =null;
	static double[] force =null;
	static int k=0;
	static int count=0;
	
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
				data = new double[count];
				data1 = new double[count];
				temp=new double[count];
				dl =new double[count];
				force = new double[count];
				while(scan2.hasNextLine()){
					String line = scan2.nextLine();
					StringTokenizer st = new StringTokenizer(line,",");
					String mc = st.nextToken();
					String name = st.nextToken();
					String area= st.nextToken();
					double ar=Double.parseDouble(area);
					String LENGTH= st.nextToken();
					double length= Double.parseDouble(LENGTH);
					rod[k]=new Rod(name,ar,length);
					if(mc.equals("AluminumBased")){
						rod[k].setMaterial(MaterialCategories.AluminumBased);
					}
					else if(mc.equals("Metals")){
						rod[k].setMaterial(MaterialCategories.Metals);
					}
					else if(mc.equals("Cellulose")){
						rod[k].setMaterial(MaterialCategories.Cellulose);
					}
					else{
						rod[k].setMaterial(MaterialCategories.Glass);
					}
					String str1 = st.nextToken();
					double temp1 =Double.parseDouble(str1);
					data[k]=temp1;
					String str2 = st.nextToken();
					double temp2 = Double.parseDouble(str2);
					data1[k]=temp2;
					k++;
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
			int tp=0;
			for(int j=0;j<k;j++){
				System.out.println("Please enter the temperature change for material: "+rod[j].getName());
				Scanner scan = new Scanner(System.in);
				temp[j]=scan.nextDouble();
				dl[j]=rod[j].calculateExpansion(data[j],temp[j]);
				double F1 = rod[0].calculateForce(data1[0],dl[0]);
				force[j] = rod[j].calculateForce(data1[j],dl[j]);
				if(force[j]>F1){
					tp=j;
					F1=force[j];
				}
				if(force[j]>0){
					System.out.println("______FIRST OUTPUT REQUIRED______\nThe expansion is: "+dl[j]+"\nThe force is: "+force[j]);
				}
			}
			System.out.println("______SECOND OUTPUT REQUIRED______\nThe greatest force required is: "+force[tp]+"\nthe rod that requires the largest force is: "+rod[tp].toString()+"\n\n______THIRD OUTPUT REQUIRED______");
			for(int j=0; j<k;j++){
				System.out.println(rod[j].toString());
			}
		}
		else if(str.equals("notready")){
		System.out.println("I can't provide data unless the text file is ready");
		}
	}
}