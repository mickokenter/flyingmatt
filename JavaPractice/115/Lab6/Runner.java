public class Runner {
	private String name;
	private int marthonTimeSeconds;
	private static int WORLD_RECORD_TIME_SECONDS=7403; // world record as of 9/29/2013
	public Runner (String n, int h, int m, int s){
		name = n;
		setTime(h, m, s);
	}
	public void setTime (int h, int m, int s){
		if (h>=0 && m>=0 && s>=0)
		marthonTimeSeconds=h*3600+m*60+s; // ADD EXPRESSION to convert the h and m and s to seconds
	}
	public String getName() { return name; }
	public int getSeconds() { return marthonTimeSeconds; }
	public int secondsSlowerThanWorldRecord(){
		return marthonTimeSeconds-WORLD_RECORD_TIME_SECONDS; // ADD CODE HERE TO CALCULATE AND RETUNR THE SECONDS SLOWER THAN THE WORLD RECORD
	}
	public static int getWorldRecord(){
		return WORLD_RECORD_TIME_SECONDS;
	}
	public String toString(){
		int a=(int)marthonTimeSeconds/3600;
		int b=(int)(marthonTimeSeconds%3600)/60;
		int c=(marthonTimeSeconds%3600)%60;
		String x="Name "+name+"\nTime "+a+":"+b+":"+c;
		return x;
	// ADD CODE HERE TO FORMAT A STRING A RETURN IT THAT HAS THE RUNNER NAME AND TIME in HH:MM:SS format
	// HINT USE INTEGER DIVISION TO GET HOURS from marthonTimeSeconds, AND THEN THE MODULUS OPERATOR % to get the integer remainder upon division
	// then continue this process to get minutes and seconds
}
}