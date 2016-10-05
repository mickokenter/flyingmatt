package inheritance;
import java.util.*;
import java.io.*;

public class TestFigures{
	public static void main(String[] args){
		try{
			ArrayList<Figure> alf=new ArrayList<Figure>();
			triangle tri=new triangle(2,"Calibri",3,"Triangle",4,5);
			alf.add(tri);
			square sq=new square(3,"Arial",2,"Square",2);
			alf.add(sq);
			rectangle rect=new rectangle(4,"Times New Roman",4,"Rectangle",6,5);
			alf.add(rect);
			triangle tri2=new triangle(3,"Arial",2,"Triangle",6,5);
			alf.add(tri2);
			square sq2=new square(4,"Arial",5,"Square",10);
			alf.add(sq2);
			
			FileOutputStream fos=new FileOutputStream("areas.dat");
			DataOutputStream dos=new DataOutputStream(fos);
			
			Figure fTemp;
			double areaTemp=0;
			fTemp=tri;
			areaTemp=fTemp.calculateArea();
			dos.writeDouble(areaTemp);
			System.out.println(tri);
			System.out.println("Its area is: "+areaTemp);
			fTemp=sq;
			areaTemp=fTemp.calculateArea();
			dos.writeDouble(areaTemp);
			System.out.println(sq);
			System.out.println("Its area is: "+areaTemp);
			fTemp=rect;
			areaTemp=fTemp.calculateArea();
			dos.writeDouble(areaTemp);
			System.out.println(rect);
			System.out.println("Its area is: "+areaTemp);
			fTemp=tri2;
			areaTemp=fTemp.calculateArea();
			dos.writeDouble(areaTemp);
			System.out.println(tri2);
			System.out.println("Its area is: "+areaTemp);
			fTemp=sq2;
			areaTemp=fTemp.calculateArea();
			dos.writeDouble(areaTemp);
			System.out.println(sq2);
			System.out.println("Its area is: "+areaTemp);
			dos.close();
			fos.close();
			
			Scanner scan=new Scanner(System.in);
			System.out.println("Type yes if you want the areas.dat file read");
			String yes=scan.nextLine();
			if(yes.equals("yes")){
				FileInputStream fis =new FileInputStream("areas.dat");
				DataInputStream dis=new DataInputStream(fis);
				System.out.println("The area read from file areas.dat is: "+dis.readDouble());
				System.out.println("The area read from file areas.dat is: "+dis.readDouble());
				System.out.println("The area read from file areas.dat is: "+dis.readDouble());
				System.out.println("The area read from file areas.dat is: "+dis.readDouble());
				System.out.println("The area read from file areas.dat is: "+dis.readDouble());
				System.out.println("finished reading");
				dis.close();
				fis.close();
			}
		}
		catch(NullPointerException npe){
			npe.getMessage();
		}
		catch(IllegalArgumentException iae){
			iae.getMessage();
		}
		catch(IOException ioe){
			ioe.getMessage();
		}
	}
}