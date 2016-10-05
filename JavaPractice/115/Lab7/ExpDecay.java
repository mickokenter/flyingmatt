public class ExpDecay{
	double L;
	double N0;
	double Nt;
	public ExpDecay(){
		L=0;
		N0=0;
		}
	public ExpDecay(double l,double n){
		if(l>=0) L=l;
		if(n>=0) N0=n;
		}
	public void setLambda(double l){
		if(l>=0) L=l;
		}
	public void setN0(double n){
		if(n>=0) N0=n;
		}
	public String toString(){
		String x="Initial Quantity: "+ N0+" "+"Lambda: "+L;
		return x;
		}
	public double futureQuantity(int t){
		double Nt=N0*Math.pow(Math.E,-L*t);
		return Nt;
		}
	public void displayTable(){
		System.out.println("TIME"+"\t"+"LAMBDA"+"\t"+"N0"+"\t"+"FUTURE QUANTITY") ;
		System.out.println("5"+"\t"+L+"\t"+N0+"\t"+futureQuantity(5));
		System.out.println("25"+"\t"+L+"\t"+N0+"\t"+futureQuantity(25));
		System.out.println("50"+"\t"+L+"\t"+N0+"\t"+futureQuantity(50));
		}
}