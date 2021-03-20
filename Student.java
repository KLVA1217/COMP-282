//Author: Ken Aquino
//Project: Student Database
public class Student implements DataItem<Student> {
	//Characterstics of students
	long idNumber;
	String name;
	boolean[] inGroup;
	
	//Constructor for Student class
	Student(String iN, String n,String iG){
		setIdNumber(Long.parseLong(iN));
		setName(n);
		setInGroup(iG);
	}
	
	public void setIdNumber(long iN) {
		idNumber=iN;
	}
	
	public long getIdNumber(){
		return idNumber;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	//sets up boolean[] by looking at each character and either putting true if t or false if f.
	public void setInGroup(String iG) {
		 inGroup = new boolean[iG.length()];
		   
			for(int i=0; i<iG.length(); i++){
		          if (iG.substring(i,i+1).equals("T")) {
		        	  inGroup[i]=true;
		          }
		          else  inGroup[i]=false;
		      }
	}
	
	public boolean[] getInGroup() {
		return inGroup;
	}
	
	public boolean memberOfGroup(int n) {
		return inGroup[n];
	}

	public int compareTo(Student s) {
			if (name.equals(s.getName())) return 1;
			else return 0;
		}
	
	//A toString method that returns a string that is a result of taking it's values but when it comes to club list iterates boolean array and if current index has the value of true then is T, if false then is f.
	public String toString() {
		String s = idNumber+", "+name+", ";
		for (int i=0; i<inGroup.length;i++) {
			String c="";
			if (inGroup[i] == true) c = "T";
			else c="F";
			s=s+c;
			}
		return s;
	}
}
