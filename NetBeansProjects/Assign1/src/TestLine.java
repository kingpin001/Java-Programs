/****************************************************************
This program demonstrates a simple "Line" class.
Here, a Line class is defined with its properties and
interface (i.e., its methods).
A main method (in TestLine) then creates instances of this Line class
and calls on the methods to demonstrate its behavior.
*****************************************************************/
import java.io.*;
import java.lang.Math.*;
class Line
{
    private int x1, y1, x2, y2; //coordinates of the line 
    private double length, angle; // length and angle
    private TwoDPoint point1, point2; //points that represent co ordinates

    //Constructor
    //Receives 4 integers which are the Line's start and end points.
    public Line(int xOne, int yOne, int xTwo, int yTwo) throws Exception
    {
	// each of these validates its argument - see below.
	setXOne(xOne);
	setYOne(yOne);
	setXTwo(xTwo);
	setYTwo(yTwo);
    } // end constructor
    //*************************************

    //method draw() calls another method called drawLine(),
    //which is assumed to be a graphics primitive on the
    //system. However, since this program will be
    //run in console mode, a text description of the Line
    //will be displayed.
    //
    public void draw()
    {
	drawLine(x1, y1, x2, y2);
    }
    //*************************************

    //Constructor
    //Receives 2 points which are co ordinates.
    public Line(TwoDPoint point1, TwoDPoint point2) throws Exception
    {
	this(point1.x, point1.y, point2.x, point2.y);
	System.out.println("test 2D point Constructor X1 = "+ point1.x);
        System.out.println("test 2D point Constructor X2 = "+ point2.x);
	System.out.println("test 2D point Constructor Y1 = "+point1.y);
	System.out.println("test 2D point Constructor Y2 = "+point2.y);
    } // end constructor
    //*************************************

    //method drawLine() simulates drawing of a line for console mode.
    //It should describe all the important attributes of the line.
    //In a graphics mode program, we would delete this and use the
    //system's Graphics library drawLine().
    //
    private void drawLine(int x1, int y1, int x2, int y2)
    {
	System.out.println("In drawline -draw a line from x of " + x1 + " and y of " + y1); 
	System.out.println("to x of " + x2 + " and y of " + y2 +" SUCCESS \n");
    }
    //*************************************

    //Method setLine() allows user to change the points of the
    //already existing Line.
    //
    public void setLine(int xOne, int yOne, int xTwo, int yTwo) throws Exception
    {
	setXOne(xOne);
	setYOne(yOne);
	setXTwo(xTwo);
	setYTwo(yTwo);
    }
    //*************************************

    // -- the individual setXXXX methods that prevent
    // any line's coordinate from being offscreen.
    // In the event of an invalid (offscreen) value,
    // that value is (silently) set to 0.
    //**************************
    public void setXOne(int xOne) throws Exception
    {
	if (xOne < 0 || xOne > 639)
	    {
		{
		    System.out.println("--TRY CATCH CAUGHT AN EXCEPTION FOR BAD VALUE OF X1");
		    throw new Exception("value "+xOne+" Was out of Bounds");
		}
	    }
	else
	    x1 = xOne;
    }
    //**************************

    public void setYOne(int yOne) throws Exception
    {
	if (yOne < 0 || yOne > 479)
	    {
		{
		    System.out.println("--TRY CATCH CAUGHT AN EXCEPTION FOR BAD VALUE OF Y1");
		    throw new Exception("value "+yOne+" Was out of Bounds");
		}
	    }
	else
	    y1 = yOne;
    }
    //**************************

    public void setXTwo(int xTwo) throws Exception
    {
	if (xTwo > 639 || xTwo < 0)
	    {
		System.out.println("--TRY CATCH CAUGHT AN EXCEPTION FOR BAD VALUE OF X2");
		throw new Exception("value "+xTwo+" Was out of Bounds");
	    }
	else
	    x2 = xTwo;
    }
    //**************************

    public void setYTwo(int yTwo) throws Exception
    {
	if (yTwo > 479 || yTwo < 0)
	    {
		System.out.println("--TRY CATCH CAUGHT AN EXCEPTION FOR BAD VALUE OF Y2");
		throw new Exception("value "+yTwo+" Was out of Bounds");
	    }
	
	else
	    y2 = yTwo;
    }
    

    // Method getLength() calculates length of the line.
    //
    public double getLength()
    {
	return Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2));//formula for calculating the length
    }
    //**************************

    // Method getAngle() calculates angle between the lines.
    //
    public double getAngle()
    {
	return Math.asin((y2-y1)/getLength());//formula for calculating the angle
    }
    //**************************

    //Now for some "get" Access methods to get individual values
    //**************************

    public int getXOne()
    {
	return x1;
    }
    //**************************

    public int getYOne()
    {
	return y1;
    }
    //**************************

    public int getXTwo()
    {
	return x2;
    }
    //**************************

    public int getYTwo()
    {
	return y2;
    }

    
} // end class Line





 
public class TestLine 
{ 
    public static void main(String args[]) 
    { 
	Line l1 = null, l2 = null, l3 = null, l4 = null; //declare 2 instances of Line class 
	TwoDPoint point1 = null, point2 = null; // 2 integers which are Line's coordinates

	try // Check for valid co ordinates
	    {
		//create a Line object 
		l1 = new Line (10, 10, 100, 100); 
		//draw it 
		l1.draw();
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println(""+e);
		System.out.println();
	   
	    } 

	try // Check for valid co ordinates
	    {
		//change start point with valid values 
		l1.setLine(5, 5, l1.getXTwo(), l1.getYTwo()); 
		//draw it again with new start point 
		l1.draw();
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println(""+e);
		System.out.println();
	    }
 
	try // Check for valid co ordinates
	    {
		//System.out.println("Testing setting left(x1) to an illegal value."); 
		//try to change xOne (x1) to an illegal value 
		l1.setXOne(3000); 
		//draw the line... an exception should be thrown
		l1.draw(); 
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println("" +e);
      		System.out.println();
	    }
	l1.draw(); 

	try // Check for valid co ordinates
	    {
		//create a second Line instance, or object 
		l2 = new Line(100, 100, 400, 400); 
		//draw 2nd line 
		l2.draw(); 
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println(""+e);
		System.out.println();
		    }
	
	try // Check for valid co ordinates
	    {
		//set a new valid yTwo for line 2 
		l2.setYTwo(479); 
		//draw 2nd line again 
		l2.draw(); 
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println("Exception: An invalid co ordinate was given. Line was not altered."); // print error message
	    }
	
	System.out.println("The angle for line 1 is " + l1.getAngle());
	System.out.println();
	System.out.println("The angle for line 2 is " + l2.getAngle());
        System.out.println();
	System.out.println("The length for line 1 is " + l1.getLength());
	System.out.println();
	System.out.println("The length for line 2 is " + l2.getLength());
	System.out.println();
	
	point1 = new TwoDPoint(10, 100);
	point2 = new TwoDPoint(5, 400);

	try // Check for valid co ordinates
	    { 	 
		    l3 = new Line(point1, point2);
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println("Exception caught-Failed to create a line with 4 invalid values-leaving with rc of 88");
		System.out.println();
	    }
	
	try // Check for valid co ordinates
	    {
  		l3.setLine(10, 100, 5, 500);
	    }
	catch(Exception e) // catch error if co ordinate is invalid
	    {
		System.out.println("Exception caught-Failed to create a line with 4 invalid values-leaving with rc of 88");
		System.out.println();
		System.exit(88);
	    }
    } // end of main 
} // end class TestLine 



class TwoDPoint
{
    public int x, y; //line co ordinates.

    // Constructor
    // Receives 2 integers which are Line's coordinates
    public TwoDPoint(int a, int b)
    {
	//storing the arguments in instance variables
	x=a;
	y=b;
    } // end Constructor
} // end class TwoDPoint
