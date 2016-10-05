


public class myDataTest {
        public static void main (String[] args) {
                myData a = new myData();
                myData b = new myData(15);
                System.out.println("a: "+a);
                System.out.println("b: "+b.toString());

                System.out.println("a find 5: "+a.findNumber(5));
                b.addNumber(12);
                b.addNumber(-5);
                System.out.println("b: "+b.toString());
                System.out.println("b find -5: "+b.findNumber(-5));

                for (int i=0; i<10; i++){
                        a.addNumber((int)(Math.random()*20-10));
                }
                System.out.println("a: "+a);
                System.out.println("max in a: "+a.getMax());
        }
}