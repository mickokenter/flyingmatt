import java.util.*;
import java.io.*;
public class FigureCost
{
	public static void main(String[] args) 
	{
		ArrayList<Figure> alf=new ArrayList<Figure>();
		Scanner scan=new Scanner(System.in);
		int count=0;
		try {
			FileOutputStream fosF = new FileOutputStream("figures.ser");
		    ObjectOutputStream oosF=new ObjectOutputStream(fosF);
			FileOutputStream fosC=new FileOutputStream("cost.ser");
		    ObjectOutputStream oosC=new ObjectOutputStream(fosC);
			System.out.println("Please enter the shape the length(or radius) and the asked value separated by space or type done");
			String line=scan.nextLine();
			do{
				StringTokenizer strtok=new StringTokenizer(line);
				String str1=strtok.nextToken();
				String str2=strtok.nextToken();
				String str3=strtok.nextToken();
				double sco=Double.parseDouble(str2);
				if(str1.equals("cube")){
					Cube c1=new Cube(str1,sco,str3);
	                alf.add(c1);
					oosF.writeObject(c1);
				}
				else{
					Sphere s1=new Sphere(str1,sco,str3);
				    alf.add(s1);
					oosF.writeObject(s1);
				}
				System.out.println("Please enter the shape the length(or radius) and the asked value separated by space or type done");
			    line=scan.nextLine();
			}while(!line.equals("done"));
			oosF.close();
		
			for(int i=0;i<alf.size();i++){
				ArrayList<String> als=alf.get(i).costToDraw();
				for(int j=0; j<als.size(); j++){
					String s1=new String(als.get(j));
					oosC.writeObject(s1);
				}
			}
			oosC.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.toString());
        }
		
		ObjectInputStream ois=null;
		FileInputStream fis=null;
		try	{
			System.out.println("If you want the file figures.ser read type the word read otherwise type anything else to stop the program");
			String st=scan.nextLine();
			if(st.equals("read")){
				fis=new FileInputStream("figures.ser");
				ois=new ObjectInputStream(fis);
				try{
					while(true){
						Figure obj=(Figure)ois.readObject();
						System.out.println(obj.toString());
					}
				}
				catch(EOFException eofe){
					System.out.println("Closing the file");
				}
				catch(ClassNotFoundException cnfe){
					System.out.println(cnfe.getMessage());
				}
			}
		}
		catch(FileNotFoundException fnfe){ 
			System.out.println(fnfe.getMessage());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		try	{
			System.out.println("If you want the file customers.ser read type the word read otherwise type anything else to stop the program");
			String st=scan.nextLine();
			if(st.equals("read")){
				fis=new FileInputStream("customers.ser");
				ois=new ObjectInputStream(fis);
				try{
					while(true){
						Customers obj=(Customers)ois.readObject();
						System.out.println(obj.toString());
					}
				}
				catch(EOFException eofe){
					System.out.println("Closing the file");
				}
				catch(ClassNotFoundException cnfe){
					System.out.println(cnfe.getMessage());
				}
			}
		}
		catch(FileNotFoundException fnfe){ 
			System.out.println(fnfe.getMessage());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
		
		try	{
			System.out.println("If you want the file cost.ser read type the word read otherwise type anything else to stop the program");
			String st=scan.nextLine();
			if(st.equals("read")){
				fis=new FileInputStream("cost.ser");
				ois=new ObjectInputStream(fis);
				try{
					while(true){
						String obj=(String)ois.readObject();
						System.out.println(obj);
					}
				}
				catch(EOFException eofe){
					System.out.println("Closing the file");
				}
				catch(ClassNotFoundException cnfe){
					System.out.println(cnfe.getMessage());
				}
			}
		}
		catch(FileNotFoundException fnfe){ 
			System.out.println(fnfe.getMessage());
		}
		catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
