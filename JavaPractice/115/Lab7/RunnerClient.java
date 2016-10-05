public class RunnerClient {
	public static void main(String[] args) {
		Runner r1 = new Runner("Bikila", 2, 15, 16);
		System.out.println(r1);
		System.out.println(r1.secondsSlowerThanWorldRecord());
		System.out.println(r1.percentageSlower()+"%");
		Runner r2 = new Runner("Radcliffe", 2, 15, 25);
		System.out.println(r2);
		System.out.println(r2.secondsSlowerThanWorldRecord());
		System.out.println(r2.percentageSlower()+"%");
		// add code here to test more
		Runner r3 = new Runner("AshKing", 2, 22, 22);
		System.out.println(r3);
		System.out.println(r3.secondsSlowerThanWorldRecord());
		System.out.println(r3.percentageSlower()+"%");
		Runner r4 = new Runner("FieldRunner", "2:23:24");
		System.out.println(r4.toStringNameTime());
		System.out.println(r4.getHour());
		System.out.println(r4.getMinute());
		System.out.println(r4.getSecond());
	}
}