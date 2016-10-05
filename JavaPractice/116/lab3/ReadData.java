package XIAQIN.QIU;
import java.io.*;
import java.util.*;

public class ReadData{
	public double[] co=null;
	public double[] y=null;
	int count=0;
	public Vector vectorData(String filename){
		try{
			File myFile=new File(filename);
			Scanner scnCount=new Scanner(myFile);
			Scanner scn=new Scanner(myFile);
			while(scnCount.hasNextLine()){
				scnCount.nextLine();
				count++;
			}
			co=new double[count];
			y=new double[count];
		}
		catch(IOException ioe){
			System.out.println("Something went wrong.")
		}
	}
//	public void setCo(double c){
//		co=c;
//	}
//	public void setY(double yInput){
//		y=yInput;
//	}
//	public double getCo(){
//		return co;
//	}
//	public double getY(){
//		return y;
//	}
//	..//
}