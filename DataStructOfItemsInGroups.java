//Author: Ken Aquino
//Project: Student Database
public interface DataStructOfItemsInGroups <Student extends DataItem<Student>> {

   public void insert(Student s);
   public void delete(int num);
   public Student find(int num);
   public int numInGroup(int num);
   public int sizeLargest();
   public int sizeSmallest();
   public String members(int num);
   public int numToReachAll();
}
