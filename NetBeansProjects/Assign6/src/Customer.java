

import java.io.*;


public class Customer implements Serializable
{
	
	// private instance variable
	private String name;
	private String ssn;
	private String address;
	private int action;
	private String code;
	private String errorMessage;
	
	// constant to define the action
	public static final int INSERT = 1;
	public static final int DELETE = 2;
	public static final int UPDATE = 3;
	public static final int SELECTALL = 4;
	public static final int LAST_ROW = 5;
	public static final int SQL_ERROR = 6;
	public static final int SUCCESS = 7;
	public static final int END = 8;
	
	// returns string representation of the customer object
	public String toString()
	{
		return name + "," +ssn + "," + address + "," + code;
	}
	
	// public get and set methods
	public int getAction() 
	{
		return action;
	}
	
	public void setAction(int action) 
	{
		this.action = action;
	}
	
	public String getCode() 
	{
		return code;
	}
	
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getSsn() 
	{
		return ssn;
	}
	
	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getErrorMessage() 
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) 
	{
		this.errorMessage = errorMessage;
	}
	
}
