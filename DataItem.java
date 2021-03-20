//Author: Ken Aquino
//Project: Student Database
public interface DataItem<Student> extends Comparable<Student> {
   
   public boolean memberOfGroup(int n);
   public String toString();
}
