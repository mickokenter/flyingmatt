import java.util.Scanner;
import java.io.*;

public class CountThreshold{
	String filename;
	double threshold;
	public CountThreshold(String name, double thres){
		setFilename(name);
		setThreshold(thres);
	}
	public void setFilename(String name){
		filename=name;
	}
	public void setThreshold(double thres){
		threshold=thres;
	}
	public String getFilename(){
		return filename;
	}
	public double getThreshold(){
		return threshold;
	}
	public int getCount() throws IOException{
		int count=0;
		File myFile=new File(filename);
		Scanner scan=new Scanner(myFile);
		while(scan.hasNext()){
			if(scan.nextDouble()>threshold) count++;
		}
		return count;
	}
	public String toString(){
		return "FileName:"+filename+" Threshold:"+threshold;
	}
}