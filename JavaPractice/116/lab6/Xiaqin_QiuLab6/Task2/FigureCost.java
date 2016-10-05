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
			FileOutputStream myFile = new FileOutputStream("output.txt", false);	
			OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(myFile));
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
				}
				else{
					Sphere s1=new Sphere(str1,sco,str3);
				    alf.add(s1);
				}
				System.out.println("Please enter the shape the length(or radius) and the asked value separated by space or type done");
			    line=scan.nextLine();
			}while(!line.equals("done"));
		
			for(int i=0;i<alf.size();i++){
				ArrayList<String> als=alf.get(i).costToDraw();
				System.out.println(alf.get(i).toString());
				osw.write(alf.get(i).toString()+"\n");
				count++;
				for(int j=0; j<als.size(); j++){
					String s1=als.get(j);
					System.out.println(als.get(j));
					osw.write(s1+"\n");
					count++;
				}
			}
			osw.flush();
			myFile.close();
		}
		catch(IOException ioe){
			System.out.println(ioe.toString());
        }
		System.out.println("Type readfile if you want to have the text file read and displayed or type no if you want the program terminated:");
		String stop=scan.nextLine();
		if(stop.equals("no")){
			System.exit(0);
		}
		else{
			try{
				FileInputStream myFile=new FileInputStream("output.txt");
				BufferedReader br=new BufferedReader(new InputStreamReader(myFile));         
				for(int i=0;i<count;i++) {
					String text=br.readLine();
					System.out.println(text);
				}
				myFile.close();
				br.close();
			}
			catch(IOException ioe){
				System.out.println(ioe.toString());
			}
		}
	}
}
