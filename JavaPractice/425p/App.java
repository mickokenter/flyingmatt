import java.sql.*;
import java.util.*;

public class App {

	public static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";  
	public static final String DBURL = "jdbc:oracle:thin:@localhost:1521:orcl";  
	public static final String DBUSER = "SYSTEM";  
	public static final String DBPASS = "123258";  
	
	
	
	
	public static void main(String args[]) throws Exception {

		System.out.println("\nLogin as:\n1. Staff\n2. User\n\nEnter 1 or 2:\n");
		Scanner scan = new Scanner(System.in);

		if(scan.nextInt() == 1) {
			System.out.println("Logged in as Xiao Huang\n");
			while (true) {
				System.out.println("What do you want?\n\n1. Search for a movie\n2. Search for a person\n3. Add a moive\n4. Add a show time\n5. Check ticket information\n6. Search for User/Staff\n7. Quit\n");
				int choice1=0, choice2=0;
				String input="", sql = "";
				int inputInt = 0;

				choice1 = scan.nextInt();

				if(choice1 == 7) {
					System.out.println("See you!\n");
					break;
				}
					

				if(choice1 == 1){
					System.out.println("Search by:\n1. Movie title\n2. Showing date\n3. Release year\n4. Actors\n5. Directors\n6. Writers\n");
					choice2 = scan.nextInt();

					if(choice2 == 1) {
						System.out.println("Please enter the movie title\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where title=" + "\'"+input+"\'";
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                 
						else{
							System.out.println("Movie not found!\n");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						
						//find the movie and print all the information
						choice1 = 0; //movie is found, exit the loop
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 2) {
						System.out.println("Please enter the showing date (MM-DD-YY)\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from SHOWING where DAY = " + "\'"+input+"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						while(rs.next()) {    
							System.out.println("Title: " + rs.getString(2) + "\nTime: " + rs.getString(4) +
									"\nRoom: " + rs.getString(1) + "\n\n");   
						}                 
						rs.close();   
						pstmt.close();   
						conn.close();    
						
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 3) {
						System.out.println("Please enter the release year\n");
						inputInt = scan.nextInt();
						sql = "select * from MOVIE where RELEASE_YEAR = " + "\'"+inputInt+"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						while(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                  
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 4) {
						System.out.println("Please enter the actor name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where ACTOR = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                  
						else {
							System.out.println("Actor not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 5) {
						System.out.println("Please enter the director name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where DIRECTOR = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                  
						else {
							System.out.println("Director not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 6) {
						System.out.println("Please enter the writer name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where WRITER = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}           
						else {
							System.out.println("Writer not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}
				}

				if(choice1 == 2) {
					System.out.println("Please enter the name of actor/writer/director:\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "select * from PERSON where NAME = " + "\'"+ input +"\'";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();   
					if(rs.next()) {    
						System.out.println("Name: " + rs.getString(1) + "\nDate of birth: " + rs.getString(2) +
								"\nGender: " + rs.getString(3) + "\nPosition: " + rs.getString(4) +
								"\n\n");   
					}                  
					else {
						System.out.println("Person not found");
					}
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Returning to menu\n");
				}

				if(choice1 == 3) {
					System.out.println("Please enter all the information for the movie (Title, PG rating, Release year, Length, Actor, Director, Writer):\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "insert into MOVIE values (" +  input +")";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();   
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Movie added\n\n");
					System.out.println("Returning to menu\n");
				}

				if(choice1 == 4) {
					System.out.println("Please enter room number, the movie title, show date and show time:\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "insert into SHOWING values (" +  input +")";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();   
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Show time added\n\n");
					System.out.println("Returning to menu\n");
				}

				if(choice1 == 5) {
					System.out.println("Please enter the ticket number:\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "select * from TICKET where SN = \'" +  input +"\'";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();  
					if (rs.next()) {
						System.out.println("Ticket found!\n\nTitle:" + rs.getString(2) +
								"\nOwner:" + rs.getString(4) + "\nDate:" + rs.getString(6) + 
								"\nTime:" + rs.getString(5) + "\nRoom:" + rs.getString(7));
					}
					else {
						System.out.println("Ticket not found!\n\n");
					}
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Returning to menu\n");
				}
				
				if(choice1 == 6) {
					System.out.println("1. Search for user\n2. Search for stuff");
					choice2 = scan.nextInt();
					if(choice2 == 1) {
						System.out.println("Please enter the name of user:\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from USERS where name = \'" + input + "\'";
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							System.out.println("\nID: " + rs.getString(1) + "\nName: " + rs.getString(2) +
									"\nPhone number: " + rs.getString(3) + "\nAddress: " + rs.getString(4) + "\n\n");
						}
						else {
							System.out.println("User not found!\n\n");
						}
					}
					else {
						System.out.println("Please enter the name of staff:\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from STAFF where name = \'" + input + "\'";
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();
						if(rs.next()) {
							System.out.println("\nStuff ID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\n\n");
						}
						else {
							System.out.println("Staff not found!\n\n");
						}
					}
					System.out.println("Returning to menu\n");
				}
			}
		}

		else { //logged in as user
			System.out.println("Logged in as Xia Qiu\n");
			while (true) {

				System.out.println("What do you want?\n\n1. Search for a movie\n2. Search for a person\n3. Buy ticket\n4. Check ticket information\n5. Quit\n");
				int choice1=0, choice2=0;
				String input="", sql = "";
				int inputInt = 0;

				choice1 = scan.nextInt();

				if(choice1 == 5){
					System.out.println("See you!\n");
					break;
				}
				
				if(choice1 == 1){
					System.out.println("Search by:\n1. Movie title\n2. Showing date\n3. Release year\n4. Actors\n5. Directors\n6. Writers\n");
					choice2 = scan.nextInt();

					if(choice2 == 1) {
						System.out.println("Please enter the movie title\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where title=" + "\'"+input+"\'";
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                 
						else {
							System.out.println("Movie not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						
						//find the movie and print all the information
						choice1 = 0; //movie is found, exit the loop
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 2) {
						System.out.println("Please enter the showing date (MM-DD-YY)\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from SHOWING where DAY = " + "\'"+input+"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						while(rs.next()) {    
							System.out.println("Title: " + rs.getString(2) + "\nTime: " + rs.getString(4) +
									"\nRoom: " + rs.getString(1) + "\n\n");  
						}                 
						rs.close();   
						pstmt.close();   
						conn.close();    
						
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 3) {
						System.out.println("Please enter the release year\n");
						inputInt = scan.nextInt();
						sql = "select * from MOVIE where RELEASE_YEAR = " + "\'"+inputInt+"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						while(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}                  
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 4) {
						System.out.println("Please enter the actor name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where ACTOR = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}       
						else {
							System.out.println("Actor not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 5) {
						System.out.println("Please enter the director name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where DIRECTOR = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}     
						else {
							System.out.println("Director not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}

					if(choice2 == 6) {
						System.out.println("Please enter the writer name\n");
						scan.nextLine();
						input = scan.nextLine();
						sql = "select * from MOVIE where WRITER = " + "\'"+ input +"\'";
						
						Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
						PreparedStatement pstmt = conn.prepareStatement(sql);   
						Class.forName(DBDRIVER);
						ResultSet rs = pstmt.executeQuery();   
						if(rs.next()) {    
							System.out.println("Title: " + rs.getString(1) + "\nPG rating: " + rs.getString(2) +
									"\nRelease year: " + rs.getString(3) + "\nLength: " + rs.getString(4) +
									"\nActor: " + rs.getString(5) + "\nDirector: " + rs.getString(6) + 
									"\nWriter: " + rs.getString(7) + "\n\n");   
						}    
						else {
							System.out.println("Writer not found");
						}
						rs.close();   
						pstmt.close();   
						conn.close();    
						System.out.println("Returning to menu\n");
					}
				}

				if(choice1 == 2) {
					System.out.println("Please enter the name of actor/writer/director:\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "select * from PERSON where NAME = " + "\'"+ input +"\'";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();   
					if(rs.next()) {    
						System.out.println("Name: " + rs.getString(1) + "\nDate of birth: " + rs.getString(2) +
								"\nGender: " + rs.getString(3) + "\nPosition: " + rs.getString(4) +
								"\n\n");   
					}   
					else {
						System.out.println("Person not found");
					}
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Returning to menu\n");
				}

				if(choice1 == 4) {
					System.out.println("Please enter the ticket number:\n");
					scan.nextLine();
					input = scan.nextLine();
					sql = "select * from TICKET where SN = \'" +  input +"\'";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();  
					if (rs.next()) {
						System.out.println("Ticket found!\n\nTitle:" + rs.getString(2) +
								"\nOwner:" + rs.getString(4) + "\nDate:" + rs.getString(6) + 
								"\nTime:" + rs.getString(5) + "\nRoom:" + rs.getString(7));
					}
					else {
						System.out.println("Ticket not found!\n\n");
					}
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("\nReturning to menu\n");
				}

				if(choice1 == 3) {
					System.out.println("All movies and showtimes are listed below:\n\n");
					sql = "select * from Showing";
					
					Connection conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt = conn.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					ResultSet rs = pstmt.executeQuery();  
					int n = 1;
					while(rs.next()) {
						System.out.println(n + ". Title:" + rs.getString(2) + "\n   Date:" + rs.getString(3) + "\n   Time:" + rs.getString(4) + "\n   Room:" + rs.getString(1) +"\n\n");
						n++;
					}
					rs.close();   
					pstmt.close();   
					conn.close();    
					
					System.out.println("Please enter the number you want:\n");
					inputInt = scan.nextInt();
					switch (inputInt) {
					case 1:
						sql = "insert into Ticket values ('1', 'LORD OF WAR', '13', 'Xia Qiu', '16:00', '12-11-14', '000004')";
						break;
					case 2:
						sql = "insert into Ticket values ('3', 'LORD OF WAR', '13', 'Xia Qiu', '12:30', '12-13-14', '000004')";
						break;
					case 3:
						sql = "insert into Ticket values ('1', 'LORD OF WAR', '13', 'Xia Qiu', '14:00', '12-12-14', '000004')";
						break;
					}

					Connection conn1 = DriverManager.getConnection(DBURL,DBUSER,DBPASS); 
					PreparedStatement pstmt1 = conn1.prepareStatement(sql);   
					Class.forName(DBDRIVER);
					pstmt1.executeQuery(); 
					System.out.println("Success! \nTicket number: 000004\n\n");
					
				}
			}
		}
	}

}
