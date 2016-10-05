public class StudentAnswerSheet{
	// instance variables
	private String name;
	private char [] answers;
	
	// class constants and class variables
	private static final char BLANK='?';
	private static final double CORRECT_POINTS=1., BLANK_POINTS=0., WRONG_POINTS=-.25;
	private static String maxName="";
	private static double maxScore=0;
	private static char [] key;
	
	//class methods
	public static void setKey(char [] answerKey){
		if(answerKey!=null){
			for(int i=0;i<answerKey.length;i++){
				key[i]=answerKey[i];
			}
		}
		else key=new char[0];
	}
	public static String highestScoringStudent(){
		return maxName;
	}
	
	//constructor
	public StudentAnswerSheet (String n, char [] a){
		if(n!=null && a!=null){
			name=n;
			for(int i=0; i<a.length; i++){
				answers[i]=a[i];
			}
		}
		else {
			name="";
			answers=new char[0];
		}
	}
	
	public String getName () {
		return name;
	}
	
	public double getScore (){
		double score=0;
		if(key.length==answers.length){
			for(int i=0; i<key.length; i++){
				if(answers[i].equals(key[i])){
					score=score+CORRECT_POINTS;
				}
				else if(answers[i].equals(BLANK)){
					score=score+BLANK_POINTS;
				}
				else score=score+WRONG_POINTS;
			}
		else score=-1000000;
		if(score>maxScore) {
			maxScore=score;
			maxName=name;
		}
		return score;
	}
	public String toString(){
	
	}
}