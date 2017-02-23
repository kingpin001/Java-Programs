                 
class Car
{ 
String color; 
int year;
//car class that I will use at end of the client pgm below
 Car(String incolor,int inyear) 
     { color = incolor;
       year=inyear;
     }
  
//override of equals is here for the equals method call below
 //this override is in the car class and we return true or false
 @Override
 public boolean equals(Object inobj) {    
     
     if(this == inobj) { //this (c1) is the object on left of the equals call below
                        // inobj is the object on right of equals call (c2,c3) below
          return true; // you are at the same object in this case
                       // so contents match
    }
  //two pointer are different, look inside object at contents  
 Car someCar = (Car)inobj; //cast it to a car object
  
 boolean answer =(this.color.equals((someCar.color)) && this.year==someCar.year); //do properties match? 
 return answer;
 }   
};  //end car class

//******************************************************************
//EXECUTION BEGINS HERE-THIS IS THE "CLIENT CLASS" OF THE CAR CLASS
//*******************************************************************
public class EqualPgm {

 public static void main(String[] args) {
  Car c4 = null;   
  
  Car c1 = new Car("blue",2014);  //get four pointers to car objects going
  Car c2 = new Car("blue",2014);
  Car c3= new Car("blue",2005);
  c4=c1;                         //c4 and c1 at same object
  
  //do i want to see if pointers on reference types are the same pointers ?
  if (c1==c4)  //when use operator == on reference types, it compare POINTERS to objects, not contents
        {System.out.println("****THESE 2 CAR POINTERS ARE EQUAL");}
  
  if (c1==c2)  //when use operator == on reference types, it compare POINTERS to objects, not contents
        {System.out.println("\n ****THESE 2 CAR POINTERS ARE EQUAL");} //not equal here
  
  //do i want to see if contents of objects are equal for reference types (exluding strings)?
  if (c1.equals(c2)) //use method "equals" to look at contents in objects
                   // **but you MUST add an overide = method to the car class (see above)
                     //for this to give you the -->correct answer
         {System.out.println("\n ****THE CONTENTS OF CARS C1 AND C2  ARE = ");}
  		
  		
  if (c1.equals(c3))  //looking at contents here with an override above
        {System.out.println("****CONTENTS OF C1 AND C3 ARE EQUAL");}
  else
        {System.out.println("\n*CONTENTS OF C1 AND C3 ARE NOT EQUAL");}
  
  
  String name="Joe";
  if (name.equals("Joe"))  //looking at contents here,
                           //no need for override-it is built in
                           // for reference type of string
     {System.out.println("\n****NAME = JOE BECAUSE OVERRIDE IS BUILT IN STRING REF TYPES -");}
  
  int num1=5;
  int num2=5;
  if (num1==num2)  //when use == on primitive types, it compare values
        {System.out.println("\n****THESE 2 ints/NUMs VALUES ARE EQUAL");}
  
  //if (num1.equals(num2))  //compiler complains if try this and pgm crashes....
      //  {System.out.println("\n****THESE 2 NUMBER VALUES ARE EQUAL");}
  }
}//end main
