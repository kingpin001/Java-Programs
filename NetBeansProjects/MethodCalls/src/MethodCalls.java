//program shows how to call methods, and return values from methods

//all parameters in java are passed by -->  value, meaning a copy is
//made of whatever ou are passing...you can pass a pointer (reference) or a value
//(primitive item), but never forget a COPY is made of whatever you pass
//http://javadude.com/articles/passbyvalue.htm

public class MethodCalls 
{
   
//****** these methods are called from main below
//signature for method 3, reference to student is passed in,
//an object of car is returned    
static Car method3(Student instudent) {
 System.out.println("\nvalue of instudent pointer inside of method 3->" + instudent.name);
 instudent.name="STEVE"; //change name in object
 instudent=null; //set the copy of pointer to null
 System.out.println("\nvalue of instudent pointer in method 3->"+ instudent);
 //create a car object and return it
 Car c1=new Car("CHEVY",2014);
 return c1;
 }//end method 3


//method 2Square version1 computers square root of a number passed in,returns double precision
static double method2Square(int innum ) {
  double returnDouble;
  return returnDouble=Math.sqrt(innum);
}
//method 2 Square version 2 computes root of all elements in a double array
static void method2Square(double [] arrayPtr ) {
  for (int i = 0; i < arrayPtr.length; i++)
  arrayPtr[i] = Math.sqrt(arrayPtr[i]);

}
//************************************************
    
    
//second version of method1 with 3 parms,  =  method overloading
 static String method1(String instring1, String instring2,String instring3) {
  String outString; //null reference to string
      
  outString=instring1+instring2+instring3;

  return outString;
 }


//**********************************************************
  //method 1, version 1 with signature of 2 strings coming in,
  //this method creates a new string of the two strings pointers passed
    //to it and returns the pointer to new string to caller
 static String method1(String instring1, String instring2) {
      String outString;               //define a reference to a string
      outString=instring1+instring2; //concatenate the 2 strings
      return outString;              //return back a string pointer
    }
    
//*********************************************************************** 
//EXECUTION STARTS HERE     
  public static void main(String[] args) {
  String string1 = "Welcome to ";
  String string2 = "JAVA";
  String string3= " in Fall 2016";
  String result; //null reference to a string
  

  //call method 1 above with 2 string pointers,get a string back 
  result = method1(string1,string2);
  
  System.out.println("This is result after returning from method 1-->" + result);
  
    
  //call method 1 (version 2 above)  with 3 strings
  result = method1(string1,string2,string3);
  System.out.println("This is result 2 after method1 call 2 -->" + result);

  //compute square root of 30 with a call to method 2 above
  int num1=30;
  Double answer=method2Square(num1);
  System.out.println("\nresult from method 2 after passing int-->" +
answer);

  //call method2 with an array of type double to get square roots
  double[] doubleArray1 = {49,9,16,25};      //define array of numbers
  method2Square(doubleArray1);                 //call method 2 with array
  for (int i = 0; i < doubleArray1.length; i++)
   System.out.println("result from method 2 after passing double array-->" + doubleArray1[i]);

  //create student object that we will pass to method 3
  //name is JOE and major is CSCI
  Student st1 = new Student("JOE", "CSCI");
  System.out.println("\nvalue of st1 pointer before calling method 3->" + st1.name);

  Car car1=null;  //null reference to a car variable
  //call method 3,change student name, and receive a car object back
  car1 =  method3(st1);
  System.out.println("\nvalue of st1 pointer AFTER calling method 3->"
+ st1);
  System.out.println("\nresult from method 3: car name-->" + car1.name);
  System.out.println("result from method 3: student name-->" + st1.name);
  }
} //end main rtn

  //************************************************************
//STUDENT CLASS
//*************************************************************
class Student {

public String name;
public String major;

  //constructor
  public Student(String name, String inmajor) {
      this.name = name;
      this.major = inmajor;
    }
}//end student

//*********************************************************
//CAR CLASS
//*********************************************************
class Car {
    public String name;
    public int year;

  //constructor
    public Car(String name, int year) {
      this.name = name;
      this.year = year;
    }
  }//end car class