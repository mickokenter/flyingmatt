class StudentAnswerSheetClient {
	public static void main(String[] args) {
		char [] key = { 'e','e','b','b','?','b','c','d','a','e' };
		StudentAnswerSheet.setKey(key);

		char [] answersA = { 'a','e','b','b','?','b','c','d','a','e' };
		StudentAnswerSheet a = new StudentAnswerSheet("Matt", answersA);
		System.out.println(a + " " + a.getScore());

		char [] answersB = { '?','?','?','?','?','?','c','d','a','e' };
		StudentAnswerSheet b = new StudentAnswerSheet("John", answersB);
		System.out.println(b + " " + b.getScore());

		char [] answersC = { 'a','a','a','a','a','a','c','d','a','e' };
		StudentAnswerSheet c = new StudentAnswerSheet("Mary", answersC);
		System.out.println(c + " " + c.getScore());

		char [] answersD = { 'e','e','b','b','?','b','c','d','a','e' };
		StudentAnswerSheet d = new StudentAnswerSheet("Fred", answersD);
		System.out.println(d + " " + d.getScore());
		System.out.println("\n");

		System.out.println("Best Student: " + StudentAnswerSheet.highestScoringStudent());

		// add more code to test all methods and special cases
	}
}