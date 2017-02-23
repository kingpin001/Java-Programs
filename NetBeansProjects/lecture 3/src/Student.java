public class Student {
//instance variables are below, all are private except college
// for access modifiers (public and private), when you write a class,
// think of what you want a 3rd party client pgm to have access to in your class
    
 public  String college;  
 private double gpa; 
 private String name,state,major,minor;     
 private int age;
 private final int tuition=5000;
 private Car carObject;
  
//below are the get and set methods to control access to private instance
//variables above when a client tries to access them
 
 //set method for name- "setter"
 public void setName(String inname) 
     { name = inname; }
 
//get method for name-"getter"
 public String getName()
     { return name ; }
	

 // set method for age..tests for age coming in
 //if variable you use coming in is same name as the private variable, must
 //use "this" to refer to current object
 public void setAge(int age) { 
   if (age<0) 
          {this.age=0;}  //if negative value coming in , set to 0
   else 
          {this.age=age;}
};
 
 //get method for age
  public int getAge() 
    { return age ; }

  // set method for state..this tests for state string coming in
 public void setState(String instate) { 
    //we can screen for improper values in set methods 
    if (instate.equals("IL")) 
       {state=instate;}
    else 
       {state="\nINVALID STATE-NOT FROM ILLINOIS";}
  };
//get method for state
public String getState() 
    { return state ; }
	
 //***tuition..read only property
public double getTuition()
  { return tuition ; }
  
//set method for minor, write only
public void setMinor(String inminor) 
   { minor = inminor; }	

//get method for major
public String getMajor()
  { return major ; }

// set method for major, if not NIU throw exception
public void setMajor(String inmajor) { 
  if (!inmajor.equals("CSCI")) 
    //throw exceptions is also possible in screening input
    throw new IllegalArgumentException("EXCEPTION: MAJOR MUST BE CSCI!!");
  else
      {major=inmajor;}
};
	
//set method for Car
public void setCar(Car incar)
    { carObject = incar; }
//get method for Came
public Car getCar() 
   { return carObject ; }


void giveMessage(){}; //rules above also apply to methods, if no
                      // public or private, the default is "package-private
                     //this means if work with packages, only classes in package
                    //can access, if not working with packages, it is private by default
}//end class Student
 