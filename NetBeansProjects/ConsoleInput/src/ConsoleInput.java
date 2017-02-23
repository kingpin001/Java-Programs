
import java.util.Scanner; //java libraries that are accessed by the code
import java.util.InputMismatchException;
public class ConsoleInput {
   public static void main(String[] args) {
    
   float decnumber;
   String name,inAnswerString;
   int int1,inNumber;
   boolean loopFlag;  //default value is false
 
   //new up scanner with this parm for console input
   Scanner scan1 = new Scanner(System.in);
   
    try
       
      {System.out.println("Enter a full name/string   ");
      //read entire text/string on the line and put into a string
      name = scan1.nextLine();
      //print out the input back to console
      System.out.println("You entered string  --> "+name);
          
      // bringing in an integer
      System.out.println("Enter an integer ");
      int1 = scan1.nextInt(); //convert string int to numeric int
      System.out.println("Your integer is ----> "+int1);
     
      //bring in a decimal number
      System.out.println("\nEnter a float pt/decimal number");
      decnumber = scan1.nextFloat(); //convert from string float to numeric float
      System.out.println("You entered decimal number  ---> "+decnumber);
      System.out.println("\n Thank you for the console input!");
 
     }
                 
   catch(InputMismatchException e)
     {  //code runs if user did not enter proper data above
            
             System.out.println("\n INPUT MISMATCH EXCEPTION-YOU HAVE NOT ENTERED VALID DATA!! ");
             scan1.nextLine(); //**this is needed for scanner to reset itself

             
     }    
   


//sample code of checking data from user on input for an integer between 1-10
  System.out.println("\n *********Let us try a loop that screens for bad data"); 
         
   loopFlag=false;  //boolean variable set to false
     
     while (loopFlag==false)   //while false (bad data)... keep spinning
       { 
                  
        try
         {
            System.out.println("\nEnter an integer between 1-10  "); 
            inNumber = scan1.nextInt();  //convert the string int to numeric integer
         
          if(inNumber<1 || inNumber>10) // we have int,check range
             System.out.println("\nINTEGER ENTERED IS OUT OF RANGE 1 TO 10 ");
          else
              loopFlag=true;  //we have a valid int in range 1-10
         }
        
        catch(InputMismatchException e)
         {  //code runs if user did not enter an integer..like.xc45
            
             System.out.println("\n CAUGHT ERROR-YOU HAVE NOT ENTERED AN INTEGER-PLEASE TRY AGAIN ");
             scan1.nextLine(); //**this is needed for scanner to reset itself
         }
       }//end while loop1
    
     System.out.println("\n *********THANK YOU FOR THE INTEGER BETWEEN 1-10"); 
     System.out.println("\n *******LET US PLAY A GAME BELOW!");
     




   //SAMPLE CODE TO START A GAME
     loopFlag=false;  //boolean variable set to false
     
     while (loopFlag==false)   //while false loop 2
       { 
         System.out.println("\nDo you want to play a game? Y/N  ");
                  
         inAnswerString = scan1.next();  //read in the letter/token
         
         if(inAnswerString.equalsIgnoreCase("y")) //if we have a valid int, check range
             System.out.println("\nPLAY GAME ....ETC, ....GAME ENDS "); //call method 
                                                        //to play game
         else 
             if(inAnswerString.equalsIgnoreCase("n"))
                 loopFlag=true;  //turn on flag
              else
                  System.out.println("\nYOUR ANSWER IS NOT VALID--PLEASE ENTER Y OR N ");  //we have a valid int in range 1-10
         } //end while2
         
     System.out.println("\nTHANK YOU AND GOOD DAY! ");   
     
  }//end main
} //end class
