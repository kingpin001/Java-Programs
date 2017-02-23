import java.util.*;
import java.util.Collections; 
import java.util.List;
//**************************************************
class Student  {
    
private String name; //instance variables for a student
private String major;
private int score;    
    
Student(String name, String major, int score) {
this.name = name;
this.major = major;
this.score = score;}

public String getName()
 { return name;
 } 
  public int getScore()
 {return score;
 } 

}; //end student class**************************************

//********************************************************
// EXECUTION STARTS HERE
//********************************************************
public class HashMaps1 {
     
  public static void main(String[] args) {
      
int sum=0;
 //create 4 student objects that we will shortly put int a hash map      
 Student st1 = new Student("ZEKE","CSCI",90); 
 Student st2 = new Student("LILLY", "CSCI",86); 
 Student st3 = new Student("BOB", "ACCY",70); 
 Student st4 = new Student("ANDY", "CSCI",92); 
 
 //HASH MAP EXAMPLE BELOW IS A HASH TABLE WITH KEY OF A STRING/ZNUMBER
 //VALUE OF AN ENTRY IS AN OBJECT OF TYPE STUDENT
 HashMap<String,Student> hashMap1 = new HashMap<>();
      
 // populate hash map with "put" method adding z num as key 
 //and a student object
 hashMap1.put("Z9055", st1);
 hashMap1.put("Z9067", st2);
 hashMap1.put("Z9033", st3);
 hashMap1.put("Z9077", st4);

 System.out.println("\nOUTPUT FROM HASHMAP STRUCTURE FOLLOWS: ");  
 System.out.println(hashMap1); 
 //let us see if key of z9067 is in hashmap
System.out.println("\nDoes HashMap have Z9067 as a key? "+
        hashMap1.containsKey("Z9067"));     

//print out info for this student object 
//because we know it is in hash table

String searchKey = "Z9067";
Student studentObject1 = hashMap1.get(searchKey);  //"get the student object
System.out.println("\nKey: " + searchKey +" value: "
        + studentObject1.getName()); 
System.out.println("\nHere is listing of all keys in keyset and object");

//let us cycle throught the hashmap to see all entries,
//get "keyset" with all keys in table first
 Set<String> keySet = hashMap1.keySet();
 //loop to find each key in table and print out the student object next to it
 for (String searchKey2 : keySet) 
 { Student studentObject = hashMap1.get(searchKey2);  //"get the student object
  System.out.println("KEY -- "+searchKey2+" NAME : "+studentObject.getName()); 
  sum =sum+ studentObject.getScore(); //sum up test scores
 }
System.out.println("\n Total Sum of Scores in Map is: " + sum);
 
 //current "date and time" can be used as a unique key/stats
String currentTime= (new Date()).toString();
HashMap<String,Student> hashMap2 = new HashMap<>();

Student st8 = new Student("SAMMY","CSCI",99); 
hashMap2.put(currentTime,st8); 

//hashMap2.put(currentTime,st2); //look at output and explain this if we put
                                 //2 elements in by time ST2 IS LILLY

System.out.println("\n Output using java Date/Time for key " );
Set<String> keySet2 = hashMap2.keySet();
 //loop to find each key in table and print out the student object next to it
 for (String searchKey3 : keySet2) 
 { Student studentObject = hashMap2.get(searchKey3);  //"get the student object
 System.out.println("KEY "+searchKey3 +" NAME : " + studentObject.getName()); 
 }
 } //end main    
 } //end arrayintro class

