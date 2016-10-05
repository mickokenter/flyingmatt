import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class CountThreshold{
	public String filename;
	public double threshold;
	public CountThreshold(String filenameInput, double thresholdInput){
		setFilename(filenameInput);
		setThreshold(thresholdInput);
	}
	public void setFilename(String filenameInput){
		if(filenameInput!=null){
			filename=filenameInput;
		}
	}
	public void setThreshold(double thresholdInput){
		if(thresholdInput>=0){
			threshold=thresholdInput;
		}
	}
	public String getFilename(){
		return filename;
	}
	public double getThreshold(){
		return threshold;
	}
	public int getCount() throws IOException{
		File localFile = new File(filename);
		Scanner scan = new Scanner(localFile);
		int count=0;
		while(scan.hasNext()){
			if(scan.nextDouble()>threshold){
				count++;
			}
		}
		return count;
	}
	public String toString(){
		return  "FileName:" + filename + " Threshold:" + threshold;
	}
}