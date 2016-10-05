import java.util.*;
import java.io.*;

public class Client{
	public static void main(String[] args){
		double ix=0, iy=0, iq=0;
		String Name="";
		ArrayList<Charge> alCharge=new ArrayList<Charge>();
		ArrayList<Point> alPoint=new ArrayList<Point>();
		E eField=new E();
		boolean flag=true;
		try{
			FileOutputStream fos=new FileOutputStream("PointElectricFields.ser");
		    ObjectOutputStream oos=new ObjectOutputStream(fos);
			Scanner inScan=new Scanner(System.in);
			System.out.println("Please input the filename:");
			String input=inScan.next();
			FileInputStream myFile=new FileInputStream(input);
			BufferedReader fileReader=new BufferedReader(new InputStreamReader(myFile));
			String line="";
			while((line=fileReader.readLine())!=null){
				StringTokenizer strtkColon=new StringTokenizer(line,":");
				String initial=strtkColon.nextToken();
				String rest=strtkColon.nextToken();
				if(initial.equals("Q")){
					StringTokenizer strtkComma=new StringTokenizer(rest, ",");
					String strtk0=strtkComma.nextToken();
					String strtk1=strtkComma.nextToken();
					String strtk2=strtkComma.nextToken();
					ix=Double.parseDouble(strtk0);
					iy=Double.parseDouble(strtk1);
					iq=Double.parseDouble(strtk2);
					Charge cTemp=new Charge(ix,iy,iq);
					alCharge.add(cTemp);
				}
				else if(initial.equals("P")){
					StringTokenizer strtkComma=new StringTokenizer(rest, ",");
					String strtk0=strtkComma.nextToken();
					String strtk1=strtkComma.nextToken();
					String strtk2=strtkComma.nextToken();
					ix=Double.parseDouble(strtk1);
					iy=Double.parseDouble(strtk2);
					Point pTemp=new Point(ix,iy,strtk0);
					alPoint.add(pTemp);
				}
			}
			myFile.close();
			fileReader.close();
			for(int i=0; i<alPoint.size(); i++){
				if(alPoint.size()>1 && i>0){
					System.out.println("--------------NEXT POINT--------------");
				}
				for(int j=0; j<alCharge.size();j++){
					System.out.println("The magnitude of electric field at point: "+alPoint.get(i).getName()+" due to charge point #"+alCharge.get(j).getID()+" is "+eField.ESingle(alCharge.get(j), alPoint.get(i)));
					System.out.println("the point coordinates are: "+alPoint.get(i).getX()+" "+alPoint.get(i).getY());
					System.out.println("The charge is: "+alCharge.get(j).getQ());
					System.out.println("The charge coordinates are: "+alCharge.get(j).getX()+" "+alCharge.get(j).getY());
					System.out.println("cosine of the angle= "+alPoint.get(i).cos(alCharge.get(j)));
					System.out.println("The value of the x component of the Electric field at point: "+alPoint.get(i).getName()+" due to charge point #"+alCharge.get(j).getID()+" is "+eField.Ex(alCharge.get(j), alPoint.get(i)));
					System.out.println("sine of the angle= "+alPoint.get(i).sin(alCharge.get(j)));
					System.out.println("The value of the y component of the Electric field at point: "+alPoint.get(i).getName()+" due to charge point #"+alCharge.get(j).getID()+" is "+eField.Ey(alCharge.get(j), alPoint.get(i)));
					System.out.println("The direction of the x-component is "+eField.ExSign(alCharge.get(j), alPoint.get(i))+" and the direction of the y component is "+eField.EySign(alCharge.get(j), alPoint.get(i)));
				}
				System.out.println("The magnitude of the resultant field is: "+eField.EResultant(alCharge, alPoint.get(i))+" for point: "+alPoint.get(i).getName());
				alPoint.get(i).setResultantField(eField.EResultant(alCharge, alPoint.get(i)));
			}
			for(int i=0; i<alPoint.size(); i++){
				oos.writeObject(alPoint.get(i));
			}
			oos.close();
			System.out.println("\nDo you want to have the PointElectricFields.ser binary coded file read? yes to read");
			Scanner scanSer=new Scanner(System.in);
			String temp=scanSer.nextLine();
			FileInputStream fis=null;
			ObjectInputStream ois=null;
			try{
				if(temp.equals("yes")){
					fis=new FileInputStream("PointElectricFields.ser");
					ois=new ObjectInputStream(fis);
					while(true){
						Point pTemp=(Point)ois.readObject();
						System.out.println(pTemp);
					}
				}
			}
			catch(EOFException eof){
				System.out.println("Closing the file");
			}
			catch(ClassNotFoundException cnf){
				System.out.println(cnf.getMessage());
			}
		}
		catch(IOException ioe){
			System.out.println("input file error");
			System.out.println(ioe.getMessage());
		}
		catch(NullPointerException npe){
			System.out.println(npe.getMessage());
		}
		catch(IllegalArgumentException iae){
			System.out.println(iae.getMessage());
		}
	}
}