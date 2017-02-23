
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.*;
import java.net.*;
import java.sql.*;				// special package to access relational database via SQL queries and commands
import javax.print.DocFlavor.URL;

public class Server extends Thread
{
//mysql database is listening on port 3306
   private static final String URL = "jdbc:mysql://courses:3306/JavaCust30";
   static Connection con;

  public final static int DEFAULT_PORT = 3306;
  protected int port;
  protected ServerSocket listen_socket;
  
  
  /******************************************************************
   * Constructor 
   * Create a ServerSocket to listen for connections.
   * Start the thread.
   ******************************************************************* */
  
  
  public Server (int aport)
  {
    /*
	if (aport == 0)
      aport = DEFAULT_PORT;
    
    this.port = aport;
    */
    try
    {
      listen_socket = new ServerSocket(3306);
		listen_socket.setSoTimeout(40000);
    }
    catch (IOException e)
    {
      fail(e, "Exception creating server socket");
    }
    
    //System.out.println("Server is listening on port---->" + port);
	System.out.println("Server is listening on port---->3306" );
	System.out.println("Server will stop hearing in 40 seconds" );
    this.start();
  }
  
  /******************************************************************
   * Fail
   * Exit with an error message when an exception occurs
   ********************************************************************/
      
  // fail ------------------------------------------------------
  // Exit with an error message when an exception occurs
  
  public static void fail(Exception e, String msg)
  {
    System.err.println(msg + ": " + e);
    System.exit(1);
  }
  
  
  /******************************************************************
   *  run
   *  The body of the server thread. Loop forever, listening for and
   *  accepting connections from clients. For each connection, create a
   *  new Conversation object to handle the communication through the new socket
   *********************************************************************/
  
  public void run()
  {
    try
    {
      while (true)
      {
        Socket client_socket = listen_socket.accept(); 
        
/***********************************************************************
 * create a Conversation object to handle this client and pass
 * it the Socket to use.  If needed, we could save the Conversation
 * object reference in a Vector. In this way we could later iterate
 * through this vector looking for "dead" connections and reclaim
 * any resources.
 **********************************************************************/
        
        Conversation conv = new Conversation(client_socket);
      }
    }
    catch (IOException e)
    {
      fail(e, "Exception listening for connections");
    }
  }
  
  // main-------------------------------------------------------
  // Start up the Server program telling it to use command line
  // port number, or set port number to zero if not specified.
  
  public static void main (String args[])
  {
    int port = 0;
    if (args.length == 1)   // user passed different port to use
    {
      try
      {
      	port = Integer.parseInt(args[0]);
      }
      catch (NumberFormatException e)
      {
      	port = 0;          
      }
    }
    
    new Server(port);
  }
} // end Server


//**************************************************************
// This class is the Thread that handles all communication with
// the client
class Conversation extends Thread
{
  protected Socket            client;
  protected InputStreamReader in;
  protected BufferedReader    bufReader;
  protected PrintWriter       out;
  
  ObjectOutputStream objOut;
  ObjectInputStream  objIn;				
  
  private Connection conn;				// JDBC class
  private Statement stmt;
  private ResultSet rs;			
  PreparedStatement pstmnt;				// to do Inserts with run-time values supplied as ? parameters
 
  
  // Constructor -----------------------------------------------
  // Initialize the streams and start the thread
  
  public Conversation(Socket client_socket)
  {
    client = client_socket;
    try
    {
	 
	objIn  = new ObjectInputStream(client.getInputStream());
	  //bufReader = new BufferedReader(objIn);
	   objOut = new ObjectOutputStream(client.getOutputStream()); 
    }
    catch(IOException e)
    {
      try
      {
      	client.close();
      }
      catch (IOException e2) {}
      System.err.println("Exception getting socket streams " + e);
      return;
    }
    
    
    /* Loading the DriverManager and Creating a Connection to the DataBase
     * 
     */
    try 
    {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	} 
    catch (InstantiationException e)
	{	
    	e.printStackTrace();
	} 
    catch (IllegalAccessException e) 
    {
		e.printStackTrace();
	}
    catch (ClassNotFoundException e) 
    {
		e.printStackTrace();
	}
	try 
	{
         String URL = "jdbc:mysql://courses:3306/JavaCust30";
		conn = DriverManager.getConnection(URL, "z1660824", "19840524");
	} 
	catch (SQLException e) 
	{
		e.printStackTrace();
	}
    // start the run loop
    this.start();
  }
  
  // run -------------------------------------------------------
  // Provide the service. Read a line sent from client,
  // reverse it, send it back.
  
  public void run()
  {
     
    try
    {
    	while (true)
    	{
    		//read a customer object
    		Customer c = (Customer)objIn.readObject();
    		String query = "";
    		
    		// if the user closed the Window
    		if(c.getAction() == Customer.END)
    		{
    			break;
    		}
    		/*
    		 * Add Button is clicked on the client this portion
    		 * will execute on the Server
    		 * */
    		else if(c.getAction() == Customer.INSERT)
    		{
    			query = "insert into cust values(\'";
    			query = query + c.getName() + "\',\'";
    			query = query + c.getSsn() + "\',\'";
    			query = query + c.getAddress() + "\',";
    			query = query + c.getCode() + ")";
    			
			try
			{
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				c.setAction(Customer.SUCCESS);
				objOut.writeObject(c);
			}
			catch(SQLException se)
			{
				c.setAction(Customer.SQL_ERROR);
				c.setErrorMessage("Duplicate Record. Try Again");
				objOut.writeObject(c);
			}
			
		}
		/*
		 * Update Button is clicked on the client this portion
		 * will execute on the Server
		 * */
		else if(c.getAction() == Customer.UPDATE)
		{
			query = "Update cust set address = ? where ssn = ?";   			
			
			pstmnt = conn.prepareStatement(query);
	
			pstmnt.setString(1, c.getAddress());
			pstmnt.setString(2, c.getSsn());
			
			int result = pstmnt.executeUpdate();
			
			if(result > 0)
				c.setAction(Customer.SUCCESS);
			else
			{
				c.setAction(Customer.SQL_ERROR);
				c.setErrorMessage("Record not found");
			}
			objOut.writeObject(c);
		}
    		/*
    		 * Remove Button is clicked on the client this portion
    		 * will execute on the Server
    		 * */
		else if(c.getAction() == Customer.DELETE)
		{
			query = "delete from cust where ssn=\'";
			query = query + c.getSsn() + "\'";
			c = new Customer();
			try 
			{
				stmt = conn.createStatement();
				int result = stmt.executeUpdate(query);
				
				if(result > 0)
					c.setAction(Customer.SUCCESS);
				else
				{
					c.setAction(Customer.SQL_ERROR);
					c.setErrorMessage("Record not found");
				}
			}
			catch (SQLException e) 
			{
				c.setAction(Customer.SQL_ERROR);
				c.setErrorMessage(e.getMessage());
				e.printStackTrace();
			}
			objOut.writeObject(c);
		}
    		/*
    		 * GetAll Button is clicked on the client this portion
    		 * will execute on the Server
    		 * */
		else if(c.getAction() == Customer.SELECTALL)
		{
			query = "select * from cust";
			
			
			try 
			{
				// Create an instance of the Statement object
				stmt = conn.createStatement();
				
				// Execute SQL and get obtain the ResultSet object
				rs = stmt.executeQuery(query);
				
				c.setAction(Customer.SUCCESS);
				objOut.writeObject(c);
				
				// Process the result set
				while (rs.next())
				{
					c = new Customer();
					String name = rs.getString("name");
					String ssn = rs.getString("ssn");
					String address = rs.getString("address");
					int code = rs.getInt("code");
					c.setName(name);
					c.setSsn(ssn);
					c.setAddress(address);
					c.setCode("" + code);
					c.setAction(Customer.SUCCESS);
					objOut.writeObject(c);					
				}
				c = new Customer();
				c.setAction(Customer.LAST_ROW);
				objOut.writeObject(c);
			} 
			catch (SQLException e) 
			{
				c = new Customer();
				c.setAction(Customer.SQL_ERROR);
				c.setErrorMessage(e.getMessage());
				objOut.writeObject(c);
				e.printStackTrace();
			}
		}
		
      }
    }
    catch (IOException e) 
	{
		e.printStackTrace();
	} 
    catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	}
    catch (Exception e) 
  	{
  		e.printStackTrace();
  	}
    finally
    {
      try
      {
        client.close();
      }
      catch (IOException e2) {}
    }  
  } // end run
 } // end Conversation
  
