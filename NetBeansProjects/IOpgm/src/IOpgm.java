//pgm shows how to read text files, text files should be in the
//the same directory as your source code at left
//one must set "file", "project properties","run", "working directory"
//to the source path here in this solution
//(so that java can find these text files....)
import java.util.Scanner;
import java.io.*;
//import java.util.InputMismatchException;

public class IOpgm {
 
 public static void main(String[] args) {
 
   int inNum;
   String inLine;
   //read file example 1
 try
    {     //read names from a file called names.txt
         File file1 = new File("names.txt");
         
         Scanner fileScanner1 = new Scanner(file1);
         while(fileScanner1.hasNextLine())       //boolean value that returns true
                                             //if the text file has a line of input to be read 
                                             //"is line of input available to be read?"
         {
             inLine = fileScanner1.nextLine(); //get the line (strings) and move to inLine
             System.out.println("NAME READ FROM FILE--> " + inLine);
             
         }
      }
      catch(FileNotFoundException e)
      {
         System.out.println("NAMES.TXT was not found...Exiting program.");
         System.exit(88);  //will show as java result :88 in output
      }
 
 //read file example 2
 try
    {     //read names again , but put into 2 fields coming in
          //assumption is each record has first name, space, lastname
         File file2 = new File("names.txt");
         
         Scanner fileScanner2 = new Scanner(file2);
         while(fileScanner2.hasNext())   //boolean returns true if some token (string, number)
                                         //is sitting in file to be read
                                        //"is something there to be read?"
         {
             String s1=fileScanner2.next(); //get first name
             String s2=fileScanner2.next();  //get last name
             System.out.println("NAMES READ FROM FILE INTO 2 FIELDS--> " + s1+" "+s2);
         }
      }
      catch(FileNotFoundException e)
      {
         System.out.println("ERROR OPENING FILE 2");
      }
 
   //read file example 3 , the file has integers and names mixed in it
    System.out.println("\nLET US READ A FILE OF NUMBERS, ONE AT A TIME");
    
    try
    {    //read numbers from a file, the file has numbers and names mixed in it
         File file1 = new File("nums.txt");
         
         Scanner fileScanner2 = new Scanner(file1);
         while(fileScanner2.hasNext())  //returns true if a token (any character)
         {                                 //is available to be read from file
                                           //"is there something to be read?"
             
            if (fileScanner2.hasNextInt())  //is it an integer? 
              {  //convert the token/number to int
                 System.out.println("Found Int to Process :" + fileScanner2.nextInt());
              }
             else
            {   
                // if no int is found, print "Not" message and the token
                  System.out.println("Not an int-- :" + fileScanner2.next());
             }
              
          }//end while
      }
      catch(FileNotFoundException e)
      {
         System.out.println("\nNUMS.TXT was not found...Exiting program.");
         System.exit(88);  //will show as java result :89 in output
      }
        
      catch(Exception e)
      {
         System.out.println("\nSOME EXCEPTION HAS OCCURRED");
         System.exit(90);  //will show as java result :89 in output
      }  
    }
}


