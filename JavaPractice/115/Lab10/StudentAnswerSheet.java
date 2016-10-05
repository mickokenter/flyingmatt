public class StudentAnswerSheet{
	private String name;
	private char[] answer;
	
	private static final char BLANK='?';
	private static final double CORRENT_POINTS=1., BLANK_POINTS=0., WRONG_POINTS=-.25;
	private static String maxName="";
	private static double maxScore=0;
	private static char[] key;
	
	public static void setKey(char [] answerKey){
		key = new char[answerKey.length];
		for(int i=0;i<answerKey.length;i++){
			key[i]=answerKey[i];
		}
	}
	public static String highestScoringStudent(){		
		return maxName;
	}
	
	public StudentAnswerSheet(String n, char[] a){
		answer = new char[a.length];
		for(int i=0;i<a.length;i++){
			answer[i]=a[i];
		}
		if(n!=null){
			name=n;
		}
	}
	public String getName(){
		return name;
	}
	public double getScore(){
		double score=0;
		if(answer.length==key.length){
			for(int i=0;i<key.length;i++){
				if(answer[i]==key[i]&&answer[i]!='?'){
					score=score+CORRENT_POINTS;
				}
				else if(answer[i]=='?'){
					score=score+BLANK_POINTS;
				}
				else{
					score=score+WRONG_POINTS;
				}
			}
			if(score>maxScore){
				maxScore=score;
				maxName=name;
			}
		}
		return score;
	}
	public String toString(){
		String str = "";
		for(int i=0;i<answer.length;i++){
			str = str+" "+answer[i];
		}
		return getName()+" "+str;
	}
}