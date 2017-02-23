import java.util.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections; 
//import java.util.Comparator; 

//pgm shows 2 types of collections in jave-array and arraylist
//**************************************************
class Student  {
    
private String name; //instance variables for a student
private String major;
int score;    
    
 Student(String name, String major, int score) {
this.name = name;
this.major = major;
this.score = score;}

public String getName()
 {
    return name;
 } 

public void setName(String name)
 {
    this.name=name;
 } 
}; //end student class**************************************

//this class will sort by the property "name" of student in array list
//in ascending order
//implements Comparator means it has a method called compare

class NameComparator implements Comparator<Student>
{  //override of compare,this is our custom made compare
   public int compare(Student s1, Student s2)
   {   //get names of two students
       String SName1 = s1.getName().toUpperCase();
       String SName2 = s2.getName().toUpperCase();
 	//ascending order by name
	return SName1.compareTo(SName2);
   } 
} //end class namecomparator 

//********************************************************
// This second comparator class compares the property "score" in descending order
//********************************************************

class ScoreComparator implements Comparator<Student>
{  //here is another implementation of compare class
   public int compare(Student st1, Student st2)
    {         //get scores of two students
 	      Integer Score1 = st1.score;
	      Integer Score2 = st2.score;
 	      //descending order by score
	      return Score2.compareTo(Score1);
    } 
} //end class scorecomparator 

//********************************************************
// EXECUTION STARTS HERE
//********************************************************

public class ArraysIntro {
      
  public static void main(String[] args) {
      
//arrays can hold both primitive and reference types,use if you have a fixed size      
//below declares the array and then allocates memory for it 
int intArray[] = new int[3];  //declare array and allocate memory for it
                              //arrays are "objects" 
intArray[0]=15;               //move in number 15 to element 0
intArray[1]=20;               //move in number 20 to element 1
intArray[2]=25;               //move in number 25,default is 0

//simple array of type "string" to hold 4  names/reference types
 String[] nameArray = new String[4];   //declare array and allocate memory for it
 nameArray[0] = "ZEKE";                //in this slot is pointer out to ZEKE
 nameArray[1] = "BILL";
 nameArray[2] = "AMY";
 nameArray[3] = "GEORGIA";             //default value is null pointer if not filled in
 
 //java allows this for looping in array..make sure an entry is not null or error
 for(String name: nameArray){
     System.out.println("HERE IS ONE NAME IN THIS NAME ARRAY-> "  +  " : " + name);    
 }
    
//SORT NAMES IN ARRAY, assuming no name is "null", pass array ptr to method sort in arrays class
Arrays.sort(nameArray);
    
//PRINT SORTED ARRAY
for(String name: nameArray){
     System.out.println("****HERE IS A SORTED NAME IN NAME ARRAY  "  +  " : " + name);    
}
    
//search for GEORGIA in the array
int index = Arrays.binarySearch(nameArray, "GEORGIA");
System.out.println("\n AFTER BINARY SEARCH, GEORGIA NAME WAS FOUND"
         + " AT POSITION  "  + index);    
      
 //java has a special "resizable array" collection called "arraylist"  
 //it can only hold references (pointers) to objects,NO primitive types allowed *
 //create 4 student objects that we will shortly put int an arraylist      
 Student st1 = new Student("ZEKE","CSCI",90); 
 Student st2 = new Student("LILLY", "CSCI",86); 
 Student st3 = new Student("BOB", "ACCY",70); 
 Student st4 = new Student("ANDY", "CSCI",92); 
 
      
 //declare an arraylist to hold objects of type student
 ArrayList<Student> studentArrayList1 = new ArrayList<>();
 

 studentArrayList1.add(st1); //add st1 pointer to list..unlimited size
 studentArrayList1.add(st2); //pointers are added in the list basically
 studentArrayList1.add(st3); 
 studentArrayList1.add(st4);
 studentArrayList1.add(st4); //add another andy (two pointers to st4)

  
 studentArrayList1.remove(4); //remove extra at index 4-last element
 
 int arrSize = studentArrayList1.size(); //size is 4
  
 Student myStudent = (Student) studentArrayList1.get(1);//get second element-Lilly
                         //***must cast it to a student object
 String studentName = myStudent.getName(); //use get and set on each object
                                //to get at private variables in object
  System.out.println(studentName);  
 //problem:how do we sort by various properties of students in the list??
  
 //here we sort by property of name of student with ascending order using a comparator class
  Collections.sort(studentArrayList1,new NameComparator());
  
 System.out.println("\nOUTPUT FROM ARRAYLIST FOLLOWS *** ");  
  //print sorted name array list 
  for(Student s:studentArrayList1){
        System.out.println("STUDENT IN ARRAYLIST SORTED BY NAME  "+s.getName());
   }
  
 //how do we modify elements in an arraylist?  ---> NEED an iterator
 // here we add 20 points to student named BOB
 // the rest of students get 15 points
  ListIterator<Student> LI = studentArrayList1.listIterator(); 
  while(LI.hasNext())
     {
         Student st = LI.next(); //get next item in list 
         
         String checkName=st.getName(); //get students name at this element
         if (checkName.equals("BOB"))
            {st.score=st.score+20;}
         else
            {st.score=st.score+15;}
     }
//print updated scores with added points  
   for(Student st:studentArrayList1){
System.out.println("ARRAYLIST SCORES WITH POINTS ADDED  "+st.getName()+" "+st.score);
   }
     
 //how to  move entries from an arraylist of students
  //to an array of type student
 Student[] studentArray1; //define a reference var. (ptr) for studentArray
 
 studentArray1 = (Student[]) studentArrayList1.toArray(new 
         Student[studentArrayList1.size()]); //fill in student array
 
 //PRINT SORTED ARRAY
for(Student mySt: studentArray1){
     
     System.out.println("********** PRINTING STUDENT ARRAY!!!!  "  +  " : " + mySt.getName());    
}
 
 //sort the student array by property "test score" in descending order
 Arrays.sort(studentArray1, new ScoreComparator());
 
 //print out sorted array
 System.out.println("\n*****SORTED OUTPUT BY TEST SCORE FROM STUDENT ARRAY FOLLOWS *** "); 
 for(Student stud: studentArray1){
  System.out.println("STUDENT ARRAY SORTED BY DESCENDING SCORE " +  stud.getName() + 
  ", SCORE : " + stud.score);    
   }
  
 //converting from array to arraylist
 ArrayList studentArrayList2 = new ArrayList(); //define new arraylist
 Collections.addAll(studentArrayList2, studentArray1); 
 
 
//how does on make a primitive type a reference type so that it
// can be put in an arraylist (such as int?)-use a wrapper class
 
Integer num1 = new Integer(10);  //wrapper class that makes int a reference type
                                 //num1 is pointer.."BIG I"
ArrayList<Integer> IntegerList1 = new ArrayList<Integer>(); //define list of Integers
 IntegerList1.add(num1);
 IntegerList1.add(20);
 IntegerList1.add(30);
 
 for(Integer intNum:IntegerList1){
        System.out.println("integer list value is -->  "+intNum);
 }
 
 //get a specific value out of the list
 int myNum1  = (int) IntegerList1.get(0);//get first element      
 System.out.println("\n Contents of first element in IntegerList is  "+ myNum1); 
 
 } //end main    
 
} //end arrayintro class
