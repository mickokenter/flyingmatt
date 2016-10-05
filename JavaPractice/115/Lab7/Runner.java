public class Runner {
	private String name;
	private int marathonTimeSeconds;
	private static int WORLD_RECORD_TIME_SECONDS=7403; // world record as of 9/29/2013
	public Runner (String n, int h, int m, int s){
		name = n;
		setTime(h, m, s);
	}
	public void setTime (int h, int m, int s){
		if (h>=0 && m>=0 && s>=0)
		marathonTimeSeconds=h*3600+m*60+s; // ADD EXPRESSION to convert the h and m and s to seconds
	}
	public String getName() { return name; }
	public int getSeconds() { return marathonTimeSeconds; }
	public int secondsSlowerThanWorldRecord(){
		return marathonTimeSeconds-WORLD_RECORD_TIME_SECONDS; // ADD CODE HERE TO CALCULATE AND RETUNR THE SECONDS SLOWER THAN THE WORLD RECORD
	}
	public static int getWorldRecord(){
		return WORLD_RECORD_TIME_SECONDS;
	}
	public String toString(){
		int a=(int)marathonTimeSeconds/3600;
		int b=(int)(marathonTimeSeconds%3600)/60;
		int c=(marathonTimeSeconds%3600)%60;
		String x="Name "+name+"\nTime "+a+":"+b+":"+c;
		return x;
		}
	public double percentageSlower(){
		double pctgslw=((double)marathonTimeSeconds/WORLD_RECORD_TIME_SECONDS-1.)*100.;
		return pctgslw;
		}
	int hh1;
	int mm1;
	int ss1;
	public Runner(String n,String data){
		String [] dat = data.split(":");
		String hh = dat[0];
		String mm = dat[1];
		String ss = dat[2];
        hh1 = Integer.parseInt(hh);
		mm1 = Integer.parseInt(mm);
		ss1 = Integer.parseInt(ss);
		name=n;
		}
	public String toStringNameTime(){
		String Y="Name: "+name+"\nTime:   "+hh1+":"+mm1+":"+ss1;
		return Y;
		} 
	public int getHour(){
		return hh1;
		}
	public int getMinute(){
		return mm1;
		}
	public int getSecond(){
		return ss1;
		}	
}