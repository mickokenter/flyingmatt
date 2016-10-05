package XIAQIN;
import XIAQIN.QIU.*;
import java.util.*;
import java.io.*;

public class RodClient{
	
	public static void main(String[] args){
		String str = args[0];
		if(str.equals("coefficients.txt")){
			ReadData rd=new ReadData();
			Vector v=rd.sortVector(rd.vectorData(str));
			for(int i=0; i<v.size(); i++){
				Rod rodTemp=(Rod) v.get(i);
				System.out.println(rodTemp);
				double eps=rodTemp.calculateExpansion(rd.co[rodTemp.getID()-1]);
				double force=rodTemp.calculateForce(rd.y[rodTemp.getID()-1], eps);
				System.out.println("The expansion is: "+eps+"\nThe force required is: "+force+"\n");
			}
			System.out.println("//FIND TEMERATURE OUTPUT");
			int[] tInput={89,156};//value to search;
			for(int i=0; i<tInput.length; i++){
				if(rd.searchVector(v, tInput[i])==-1){
					System.out.println("\nTemperature "+tInput[i]+" value was not found");
				}
				else{
					Rod rodT=(Rod) v.get(rd.searchVector(v, tInput[i]));
					System.out.println("\nThe object was found and it is: "+rodT);
				}
			}
		}
	}
}
