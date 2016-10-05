package XIAQIN.QIU;
import XIAQIN.QIU.*;
import java.io.*;
import java.util.*;

public class ReadData{
	public double[] co=null;
	public double[] y=null;
	int count=0;
	public Vector vectorData(String filename){
		Vector v=new Vector();
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
			for(int i=0; i<count; i++){
				String line=scn.nextLine();
				StringTokenizer strtk=new StringTokenizer(line,",");
				String mcstr = strtk.nextToken();
				String name = strtk.nextToken();
				String aTemp= strtk.nextToken();
				String lTemp= strtk.nextToken();
				String coTemp = strtk.nextToken();
				String yTemp = strtk.nextToken();	
				String tTemp = strtk.nextToken();			
				double area=Double.parseDouble(aTemp);
				double length=Double.parseDouble(lTemp);
				int t=Integer.parseInt(tTemp);
				Rod rodTemp=new Rod(t,name,area,length);
				v.addElement(rodTemp);
				MaterialCategories mc=MaterialCategories.valueOf(mcstr);
				rodTemp.setMaterial(mc);
				co[i]=Double.parseDouble(coTemp);
				y[i]=Double.parseDouble(yTemp);
			}
		}
		catch(IOException ioe){
			System.out.println("Something went wrong.");
		}
		catch(NullPointerException np){}
		catch(IllegalArgumentException ia){}
		return v;
	}
	public Vector sortVector(Vector v){
		int index=0;
		for (int i=0; i<v.size(); i++){
			index=i;
			Rod rodTempIndex=(Rod) v.get(i);
			for (int j=i+1; j<v.size(); j++){
				Rod rodTemp=(Rod) v.get(j);
				if (rodTemp.getTemperature() > rodTempIndex.getTemperature()){
					index=j;
					rodTempIndex=rodTemp;
				}
			}
			v.set(index, v.get(i));
			v.set(i, rodTempIndex);
		}
		return v;
	}
	public int searchVector(Vector sv, int key){
		int start = 0, end = sv.size() - 1;
		while ( end >= start ){
			int middle = ( start + end ) / 2;
			Rod rodMiddle=(Rod) sv.get(middle);
			if ( rodMiddle.getTemperature() == key )
				return middle;
			else if ( rodMiddle.getTemperature() < key )
				end = middle - 1;
			else
				start = middle + 1;
		}
		return -1;
	}
}
