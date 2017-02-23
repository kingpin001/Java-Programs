
//car class that i will use at end of client pgm below
class Car{ 

//constructor
 Car(String incolor) 
     { color = incolor; }
        
String color; 

};  //end car class
 
//******************************************************************
//EXECUTION BEGINS HERE-THIS IS THE "CLIENT CLASS" OF THE STUDENT CLASS
//*******************************************************************
public class Properties {

 public static void main(String[] args) {
  Student st1= new Student(); //create one student object and see what
                           //we can and can not access in students 
                           //instance variables
  
  st1.college="NIU";  //college is public have total access to it
  //st1.gpa=4.0;  //can not access, it is private
  
  st1.setName("JOE SMITH");  //note name is private in the class
  st1.setAge(22); //set a valid age
    
  Integer joeAge=st1.getAge();    //get his age out
  System.out.println("JOE HAS AGE OF "+joeAge);
		
  st1.setAge(-10);  //set a bad age
  joeAge=st1.getAge(); //age was set to 0 by property
  System.out.println("JOE HAS AGE OF "+joeAge);
		
  st1.setState("IL"); //set a valid state
  //st1.setState("WI"); //set an invalid state and get a message
  String s1=st1.getState(); //get state out
  System.out.println(s1);  //print what state is
  
  double joeTuiton= st1.getTuition();  //tuition is read only
		
  //st1.setMinor("MATH"); //write only
  //st1.setMajor("PSYCHOLOGY"); //can throw exception as well in this case 
  st1.giveMessage(); //same rules apply to methods private/public
  
  Car c1 = new Car("blue");  //get c1 pointing to car object
  c1.color="BLUE";    //fill in the color of this car object
  st1.setCar(c1);    //fill in car object in the student object
		
  Car c2 = st1.getCar(); //get it back out with c2 pointing to car ob
		
    
  }
}//end main


