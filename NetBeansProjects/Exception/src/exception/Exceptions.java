//PGM SHOWS EXCEPTIONS AND THE USE OF TRY/CATCH
package exception;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Exceptions {
static String inSchool = null;
static int num1=5,num2=0;

  //MAIN EXECUTION STARTS HERE
  public static void main(String[] args) {

     
    //num2 = num1/0;
    //TRY/CATCH1..can you divide by 0 in java?
    /*try
    {
      num2 = num1/0;  //take out try catches on this and see output
      //77throw new Exception("Generic Exception Message");
    }
    
    catch(ArithmeticException e)
    {  //catching it allows you to gain control and  no crash ending
        //you can shut down gracefully or continue on if you want
    System.out.println("ARITHMETIC EXCEPTION OCCURRED-CAN NOT DIVIDE BY 0");
        System.exit(100); //end pgm here with return code of 100
    }
    catch (Exception e)
    {
        System.out.println("SOME EXCEPTION OCCURRED--PROGRAM IS TERMINATING ") ;
        System.exit(200); //end pgm here with return code of 100
    }*/



  //TRY/CATCH 2 SHOWS EXCEPTION THAT OCCURS WITH BAD NUMERIC INPUT FROMCONSOLE
    /*try
    {
      int inAge;
      System.out.println("\nENTER A NUMERIC AGE --> ");  //get an age
      Scanner scan1 = new Scanner(System.in); //set up scanner obj for con input

      inAge = scan1.nextInt();//read in the interger

    }
    catch (InputMismatchException e)
    { //catch error if we have bad input from the console
    System.out.println(" InputMismatchException occurred -PROGRAM ENDING ");
      System.exit(250); //end pgm here with return code of 250
    }

*/
    // TRY/CATCH 3 here checks a school for input..school should be NIU  or niu
  try
    {
      System.out.println("\nENTER YOUR SCHOOL --> "); //ask user for school
      Scanner scan1 = new Scanner(System.in); //set up scanner obj forsole input

      inSchool = scan1.nextLine();//read in the school
      checkInput();  // we have input from user, call a method below to
                      //verify NIU was spelled correctly

    }
  catch (JLSpellException spellexceptobj)  // catch any bad spellings
    {
        //print message and items in the spell exception obj that exists
      System.out.println("\n**SCHOOL EXCEPTION OCCURRED WITH THIS BAD NAME--> "
              + spellexceptobj.getBadName());
      System.out.println("\n***STACK TRACE FOLLOWS**** " );
                spellexceptobj.printStackTrace();
    }

    finally {

          System.out.println("--- PGM IS SHUTTING DOWN IN FINALLY BLOCK---");
          System.exit(999); //end pgm here with return code of 999
    }

  }//end main rtn
//************************************************************************
//method checkInput is called from above to check school spelling for NIU/niu
private static void checkInput() throws JLSpellException
  {
    if (inSchool.equalsIgnoreCase("NIU"))
      System.out.println("OK - YOU SPELLED NIU CORRECTLY");
    else
      throw new JLSpellException(inSchool); //throw a custom made exception
  }

}  // end main class

//****************************************************************
// Custom exception class that descends from Java's Exception class.
//****************************************************************

class JLSpellException extends Exception
{
  private String badSchoolName; //instance variable for this exception

//----------------------------------------------
// Default constructor 1 - initializes string in object to "unknown"

  public JLSpellException()
  {
    super();            // call superclass constructor
    badSchoolName = "unknown";
  }

//-----------------------------------------------
// Constructor 2 receives message that is saved in an instance variable.
public JLSpellException(String inbadSchoolName)
{
    super(inbadSchoolName);    // call super class constructor
    badSchoolName = inbadSchoolName;  // save message
}

//------------------------------------------------
// get method for private variable badSchoolName

  public String getBadName()
  {
    return badSchoolName;
  }
}