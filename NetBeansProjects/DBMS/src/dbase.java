
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
//cust database                    name    ssn(key)    address      code (int)
//CLASSPATH=/usr/share/java/*:.     <-- this must be typed in at console to  find database classes
public class dbase  {

      

  //mysql database is listening on port 3306
   private static final String URL = "jdbc:mysql://courses:3306/JavaCust10";
   static Connection con;

public static void main (String args[]) {

  // Get a driver manager object for database-below i codes for "mysql"dbase
         try {
                   Class.forName("com.mysql.jdbc.Driver").newInstance();
           } catch (ClassNotFoundException e) {
                   System.err.println("Exception loading DriverManager class " + e);
                   return;
           } catch (InstantiationException e) {
                   System.err.println("Exception loading DriverManager class " + e);
                   return;
           } catch (IllegalAccessException e) {
                   System.err.println("Exception loading DriverManager class " + e);
                   return;
}

           // connect to dbase using the DriverManager
           try {
           con = DriverManager.getConnection(URL, "z1660824", "19840524");
           } catch (SQLException e)
           {  System.err.println("Exception connecting to database mgr " + e);
                   return;
           }
//***************************************************************
//           LIST CONTENTS OF DATABASE RECORDS
//***************************************************************
           Statement stmnt;
           int recordCount = 0;
           String message = "";
           try {
                   // Create new "statement"
                   stmnt = con.createStatement();
                   ResultSet rs = stmnt.executeQuery("SELECT * FROM cust");

                // While there are records in result set add them to string message
                   while (rs.next()) {
                           recordCount ++;
                           message += rs.getString(1) + "; ";
                           message += rs.getString(2) + "; ";
                           message += rs.getString(3) + "; ";
                           message += rs.getString(4) + "\n";
                   }

                   // Close the statement
                   stmnt.close();

           } catch (SQLException e) {
                   System.err.println("SQLException " + e);
           }
//*********************************************************************
//            ADD A RECORD
//*********************************************************************
           int numAdded;
          try {

             // Create new statement
              stmnt = con.createStatement();
              String insertSQL =("INSERT INTO cust VALUES('JACKSON','77777777','15 LOCUST DEKALB',100005)");
              numAdded =  stmnt.executeUpdate(insertSQL);

              stmnt.close();

           } catch (SQLException e) {
                   System.err.println("INSERT SQLException " + e);
           }
//*********************************************************************
//           DELETE A RECORD
//*********************************************************************
         try{

         PreparedStatement preStatement;
         preStatement = con.prepareStatement("DELETE from cust WHERE name=?");
         preStatement.setString(1, "RICK");

        int NumDeleted = preStatement.executeUpdate();
        preStatement.close();

        }catch (SQLException e){
          System.err.println("INSERT SQLException " + e);
       }
//***************************************************************
//           MODIFY A RECORD-SETTING A NEW NAME  FOR A GIVEN SSN
//***************************************************************
  try{
         PreparedStatement preStatement2;                                     //PREPARED STATEMENTS ARE FAST
         String updateTableSQL = "UPDATE cust SET NAME = ? WHERE SSN = ?";    //? ARE PLACEHOLDERS
         preStatement2 = con.prepareStatement(updateTableSQL);
         preStatement2.setString(1, "ANDERSON3");
         preStatement2.setString(2, "999999999");

        int NumUpdated = preStatement2.executeUpdate();

         preStatement2.close();

        }catch (SQLException e){
          System.err.println("INSERT SQLException " + e);
       }

   } //endmain

}//end class


