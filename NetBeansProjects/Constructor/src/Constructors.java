class Student
{
String name;              //properties of a student, also called 
String major;             // also called "instance variables"
int age;

//constructor 1-default constructor-fills in name & major, age unchanged
Student()
{
          name="not given";
          major="undecided";
    
}

//constructor 2 where client passes in values in constructor
Student(String inname, String inmajor,int age){ 
	                 //constructor 2 for base class,3 parms expected
          name=inname;
          major=inmajor;
          this.age=age; //”this” means current instance
 }

//constructor 3 receives an age, calls default constructor1, and other stuff 
Student(int inage){ 
	  this();  //call default constructor-must match the signature in call
                //must be first statement coded
     age=inage;
     
     GiveMessage(); //can also call methods from constructors
          
 }//end constructor 3

public void GiveMessage(){  //this is an instance method...methods are action
	                      
System.out.println("\nGiveMessage Method was called in this instance of student "+name);
  }
};  //end student class
//******************************************************
//execution starts at MAIN here
//******************************************************
public class Constructors {
    
 public static void main(String[] args) 
 {
  int studentage=22; //integers are primitive types
        
  Student st1 = new Student();  //call default constructor 1 
  System.out.println(st1.name+" "+st1.age+" "+st1.major);   //print out student 1 info          
               
  Student st2 = new Student("BOB","CSCI",studentage); //call  constructor 2
  System.out.println(st2.name+" "+st2.age+" "+st2.major);   //print out student 2 info           	   
                       
  Student st3 = new Student(23); //call default constructor 3 
  System.out.println(st3.name+" "+st3.age);  //print student 3 info
 } //end class constructor
}