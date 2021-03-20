//Author: Ken Aquino
//Project: Student Database
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner; 

public class Driver {
	
	static GroupData Database = new GroupData();
	
	public static void main(String[] args) {
		// The name of the file to open.
        String fileName = "data.txt";

     // This will reference one line at a time
        String line = null; 

        try {
        	// initializes a file reader
            FileReader fileReader = new FileReader(fileName);
            
            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //Program will keep reading line by line until there are no lines
            while((line = bufferedReader.readLine()) != null) {
            	
            	//Puts currently read line into a string array seperated by commas, since information is seperated by commas
            	String[] currentStudentInfo = line.split(",");
            	
            	//Trims all the strings to avoid possible errors
            	for(int i=0; i<3 ;i++) {currentStudentInfo[i] = currentStudentInfo[i].trim();}
            	
            	//creates a student object based off of newly read info
            	Student currentStudent = new Student(currentStudentInfo[0],currentStudentInfo[1],currentStudentInfo[2]);
            	
            	//inserts the current student into the database
            	Database.insert(currentStudent);
            	
            }   
            //closes bufferedReader to avoid leaks
            bufferedReader.close();   
        }
        //Error is thrown if unable to find file
        catch(FileNotFoundException ex) {
        	System.out.println("Unable to open file '" + fileName + "'");
        }
        //Error is thrown when unable to read the file
        catch(IOException ex) {
        	System.out.println("Error reading file '" + fileName + "'");
        }
        
        //Initializes a scanner for user input
        Scanner scan = new Scanner(System.in);
        //Is in indication for the program to start while loop
        String s = "start";
        
        //program will continue to function as long as the user input is one of the function of the program such as help to display options, drop, size, members, largest etc.
        while(s.equals("start")||s.equals("help")||s.equals("add") || s.equals("drop")|| s.equals("find") ||s.equals("size")|| s.equals("members") || s.equals("largest")|| s.equals("smallest") || s.equals("cover")){
        	
        	// Asks for user input and a hint is given
     	   System.out.println("What would you like to do? (Type help for options)");
     	   
     	   //takes user input
     	   s = scan.next();
     	   
     	   //The program will function depending on user input
     	   
     	   	if (s.equals("help")){
     	   		System.out.println("Your options are:"
     	   				+"\nadd      (adds a student)"
     	   				+"\nmembers  (outputs the members of a group)"
     	   				+"\nquit     (ends the program)");
     	   	}
     	   	
     	   
     	   	if (s.equals("add")) {
     	   	
        		String idNum, nam, clubs;
     	   		
        		System.out.println("ID Number?");
        			idNum= scan.next();
        			
        		System.out.println("Name?");
        			nam = scan.next();

        		System.out.println("Clubs?");
        		clubs = scan.next();
        		
        		Student studentToAdd = new Student(idNum, nam, clubs); 
        		Database.insert(studentToAdd); 
        	}
        	
     	   if (s.equals("drop")) {

     		   int idNum;
    	   		
     		   System.out.println("ID Number?");
     		   idNum= scan.nextInt();
       			
     		   Database.delete(idNum);
       		}
     	   
     	  if (s.equals("find")) {

    		   int idNum;
   	   		
    		   System.out.println("ID Number?");
    		   idNum= scan.nextInt();
      			
    		   System.out.println(Database.find(idNum).toString());
      		}
     	   
     	  if (s.equals("size")) {
    		   int groupNum;
   	   		
    		   System.out.println("group?");
    		   groupNum= scan.nextInt();
      			
      		   System.out.println(Database.numInGroup(groupNum));
      		}
     	   	
     	   	if (s.equals("members")) {
        		System.out.println("Group?");
        		
        		int num = scan.nextInt();
        		System.out.println(Database.members(num));
        	}
     	   	
     	 	if (s.equals("largest")) {
        		System.out.println(Database.sizeLargest());
        	}
     	 	
     	 	if (s.equals("smallest")) {
        		System.out.println(Database.sizeSmallest());
        	}
     	 	
     	 	if (s.equals("cover")) {
        		System.out.println(Database.numToReachAll());
        	}
        }
        
        scan.close();
    }
}
