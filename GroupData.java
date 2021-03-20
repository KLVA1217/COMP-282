//Author: Ken Aquino
//Project: Student Database
import java.util.ArrayList;

public class GroupData implements DataStructOfItemsInGroups<Student>{
	//Where all Students are stored
	ArrayList<Student> Group = new ArrayList<Student>();
	
	public void insert(Student s){
		Group.add(s);
	}
	
	//Deletes student from the database given the id number. Goes through database looking for id number until it finds a student with a match and then deletes and leaves the method.
	public void delete(int num) {
		for (Student s: Group) {
			if (s.getIdNumber()==num) {Group.remove(s); break;}
		}	
	}

	//Goes through database looking for id number until it finds a student with a match, it will then return the student object.
	public Student find(int num) {
		Student studentToReturn=null;
		for (Student st: Group) {
			if (st.getIdNumber()==num) {studentToReturn=st;}
		}
		return studentToReturn;
	}

	//given the group/club number it will go through all the students' club lists and go that index and count how many times it has seen true. Then it returns the value.
	public int numInGroup(int num) {
		int count=0;
		for (Student st: Group){
			if (st.memberOfGroup(num) == true) {count++;}
		}
		return count;
	}

	//Assuming that there are only four clubs, the program goes through the entire list while counting how many are in a group. At the end of the list, if the current count is bigger than the largest recorded number, the current count becomes the new largest number, else restart count.
	public int sizeLargest() {
		int largest=0;
		int count=0;
		for(int i=0;i<4;i++) {
			for (Student st: Group) {
				if (st.memberOfGroup(i) == true) {count++;}
			}
			if (count>largest) {largest=count; count=0;}
			else {count=0;}
		}	
		return largest;
	}

	//Assuming that there are only four clubs, the program goes through the entire list while counting how many are in a group. At the end of the list, if it's the programs first count (meaning that the count is zero) the current count is the smallest number, if the current count is smaller than the smallest recorded number, the current count becomes the new smallest number, else restart count.
	public int sizeSmallest() {
		int smallest=0;
		int count=0;
		for(int i=0;i<4;i++) {
			for (Student st: Group) {
				if (st.memberOfGroup(i) == true) {count++;}
			}
			if (smallest==0 && i==0) {smallest=count;}
			else if (count<smallest) {smallest=count; count=0;}
			else {count=0;}
		}	
		return smallest;
	}

	//Given the group number, the program will go through the database looking at the students' club list at the given index/group number and if true will compile a string of students and their information. Once complete it will return the string.
	public String members(int num) {
		String mems="";
		for (Student s: Group) {
			if (s.memberOfGroup(num) == true) mems=mems+"\n"+s.toString();
		}
		return mems;
	}

	//the program will go through the databse and look at the current group and count how many times it's seen true. If the count is the same as the list of students, it will assume that all the students are in that club and adds plus 1 to the group counter. Once it has gone through all the groups it will return the group counter value.
	public int numToReachAll() {
		int groupcount=0;
		int count=0;
		for(int i=0;i<4;i++){
				count=0;
				for (Student st: Group) {
					if(st.memberOfGroup(i) == true) {count++;}
					if(count == Group.size()) {groupcount++;}
				}	
		}
		return groupcount;
	}	
}
