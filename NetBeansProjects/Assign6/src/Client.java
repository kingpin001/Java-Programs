import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.*;


public class Client extends JFrame implements ActionListener, WindowListener
{
	// final variable representing the server and port
    public final String SERVER = "hopper.cs.niu.edu";
    public final int DEFAULT_PORT = 3306;
    
    /********* GUI component for the JFrame  *********/
	
	private JPanel leftPanel, rightPanel;
    
	private JLabel lblName, lblSSN, lblCode, lblAddress, lblError, lblEmpty;
    private JTextField fieldName, fieldSSN, fieldCode, fieldAddress;
    private JTextArea displayArea;
    private JButton btnAdd, btnRemove, btnUpdate, btnGetAll;
    
    // Initializing the Socket and 
    private Socket client;
    private ObjectOutputStream objOut;
    private ObjectInputStream  objIn;
        
   /* 
    * Constructor for CLient Class
    * */
    public Client()					 
    {
        Container contentPane = getContentPane();
        
        leftPanel  = new JPanel();
        rightPanel  = new JPanel();

        lblName = new JLabel("Name");
        lblSSN = new JLabel("SSN");
        lblCode = new JLabel("Code");
        lblAddress = new JLabel("Address");
        lblError = new JLabel();
        lblEmpty = new JLabel();
        
        fieldName = new JTextField();
        fieldSSN = new JTextField();
        fieldCode = new JTextField();
        fieldAddress = new JTextField();
        
        btnAdd = new JButton("Add");
        btnRemove = new JButton("Remove");
        btnUpdate = new JButton("Update");
        btnGetAll = new JButton("Get All");
        
        displayArea = new JTextArea(15,30);
        
        // adding action listener to the buttons
        btnAdd.addActionListener(this);
        btnRemove.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnGetAll.addActionListener(this);
        
        // adding window listener to the frame
        this.addWindowListener(this);
        
        // adding the GUI components to the Frame
       // contentPane.setLayout(new GridLayout(2, 1));
       // leftPanel.setLayout(new GridLayout(7, 2, 5 , 5));
       // rightPanel.setLayout(new FlowLayout());
	   
	   contentPane.setLayout(new FlowLayout());
        leftPanel.setLayout(new GridLayout(7,2,1,1));
       // leftPanel.setLayout(new FlowLayout());
        rightPanel.setLayout(new FlowLayout());
        
        leftPanel.add(lblName);
        leftPanel.add(fieldName);
        
        leftPanel.add(lblSSN);
        leftPanel.add(fieldSSN);
        
        leftPanel.add(lblAddress);
        leftPanel.add(fieldAddress);
        
        leftPanel.add(lblCode);
        leftPanel.add(fieldCode);
        
        leftPanel.add(lblError);
        leftPanel.add(lblEmpty);
        
        leftPanel.add(btnAdd);
        leftPanel.add(btnRemove);
        leftPanel.add(btnUpdate);
        leftPanel.add(btnGetAll);
        
        rightPanel.add(displayArea);
        rightPanel.add(new JScrollPane(displayArea));
        contentPane.add(leftPanel);
        contentPane.add(rightPanel);
        
    }


    public static void main(String args[])
    {
    	Client mainWindow = new Client();

        // setting the Parameters for the Frame
        mainWindow.setTitle("Customer Database");
        mainWindow.setSize(400, 600);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setVisible(true);
		mainWindow.setLocationRelativeTo(null);
    }
    
    /* * * * * * * * * * * *
     * this method will clear all text fields
     * * * * * * * * * * * */ 
    private void clearAllTextField()
    {
    	fieldName.setText("");
    	fieldAddress.setText("");
    	fieldCode.setText("");
    	fieldSSN.setText("");
    }
    
    /* * * * * * * * * * * *
     * This method will be invoked when any of the button will be clicked
     * * * * * * * * * * * */

    public void actionPerformed(ActionEvent e)
    {
    	// if the button was clicked for the first time
    	// then we will establish the connection first
    	// and set the IO stream
    //	if(client == null)
    //	{
	Socket client=null;
    		try 
    		{
			//	client = new Socket(SERVER, DEFAULT_PORT);
			        client = new Socket("hopper.cs.niu.edu", 3306);
				objOut = new ObjectOutputStream(client.getOutputStream()); 
				objIn  = new ObjectInputStream(client.getInputStream()); 
    		} 
    		catch (UnknownHostException e1) 
    		{
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Can not establish connection with Server");
        		return;
			} 
    		catch (IOException e1) 
    		{
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "Can not establish connection with Server");
        		return;
			}
    	//}
    	
    	
    	/* * * * * * * * * * * *
         * add button was clicked
         * * * * * * * * * * * */ 
 
        if(e.getSource() == btnAdd)
        {
        	if(fieldName.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The Name can't be empty");
        		
        		return;
        	}
        	if(fieldSSN.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The SSN can't be empty");
        		
        		return;
        	}
        	if(fieldAddress.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The Address can't be empty");
        		
        		return;
        	}
        	if(fieldCode.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The Code can't be empty");
        		
        		return;
        	}
        	
        	try
        	{
        		int x = Integer.parseInt(fieldCode.getText().trim());
        	}
        	catch(Exception ex1)
        	{
        		JOptionPane.showMessageDialog(null, "The Code Must be a valid integer");
        		
        		return;
        	}
        	
        	Customer c = new Customer();
        	c.setAction(Customer.INSERT);
        	c.setAddress(fieldAddress.getText().trim());
        	c.setCode(fieldCode.getText().trim());
        	c.setName(fieldName.getText().trim());
        	c.setSsn(fieldSSN.getText().trim());
        	
        	try 
        	{
				objOut.writeObject(c);
				objOut.flush();
			} 
        	catch (IOException e1) 
			{
				e1.printStackTrace();
			}
        	
        	try 
        	{
				Customer c1 = (Customer)objIn.readObject();
				if(c1.getAction() != Customer.SUCCESS)
				{
					lblError.setText(c1.getErrorMessage());
					
				}
				else
				{
					lblError.setText("Insert Successful");
					
				}
				clearAllTextField();
			} 
        	catch (ClassNotFoundException e1) 
        	{
				
				e1.printStackTrace();
			} 
        	catch (IOException e1) 
        	{
				
				e1.printStackTrace();
			}
        }
        
        /* * * * * * * * * * * *
         * remove button was clicked
         * * * * * * * * * * * */
        else if(e.getSource() == btnRemove)
        {
        	if(fieldSSN.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The SSN can't be empty");
        		return;
        	}
        	Customer c = new Customer();
        	c.setAction(Customer.DELETE);
        	c.setSsn(fieldSSN.getText().trim());
        	try {
				objOut.writeObject(c);
				objOut.flush();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
        	
        	try 
        	{
				Customer c1 = (Customer)objIn.readObject();
				if(c1.getAction() == Customer.SQL_ERROR)
				{
					lblError.setText(c1.getErrorMessage());
					
				}
				else
				{
					lblError.setText("Successfully Removed");
					
				}
			} 
        	catch (ClassNotFoundException e1) 
        	{
				e1.printStackTrace();
			} 
        	catch (IOException e1) 
        	{
				e1.printStackTrace();
			}
        	clearAllTextField();
        }
        /* * * * * * * * * * * *
         * update button was clicked
         * * * * * * * * * * * */
        else if(e.getSource() == btnUpdate)
        {
        	if(fieldSSN.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The SSN can't be empty");
        		return;
        	}
        	
        	if(fieldAddress.getText().trim().length() == 0)
        	{
        		JOptionPane.showMessageDialog(null, "The Address can't be empty");
        		return;
        	}
        	
        	Customer c = new Customer();
        	c.setAction(Customer.UPDATE);
        	c.setSsn(fieldSSN.getText().trim());
        	c.setAddress(fieldAddress.getText().trim());
        	
        	try 
        	{
				objOut.writeObject(c);
				objOut.flush();
			} catch (IOException e1) 
			{
				e1.printStackTrace();
			}
        	
        	try 
        	{
				Customer c1 = (Customer)objIn.readObject();
				if(c1.getAction() == Customer.SQL_ERROR)
				{
					lblError.setText(c1.getErrorMessage());
					
				}
				else
				{
					lblError.setText("Update successful");
					
				}
			} 
        	catch (ClassNotFoundException e1) 
        	{
				e1.printStackTrace();
			} 
        	catch (IOException e1) 
        	{
				e1.printStackTrace();
			}
        	clearAllTextField();
        }
        /* * * * * * * * * * * *
         * GetAll button was clicked
         * * * * * * * * * * * */
        else if(e.getSource() == btnGetAll)
        {
        	
        	Customer c = new Customer();
        	c.setAction(Customer.SELECTALL);
        	
        	try
        	{
				objOut.writeObject(c);
				objOut.flush();
			} 
        	catch (IOException e1) 
        	{
				e1.printStackTrace();
			}
        	
        	lblError.setText("");
        	clearAllTextField();
        	try 
        	{
        		displayArea.setText("");
        		Customer c1 = (Customer)objIn.readObject();
        		if(c1.getAction() == Customer.SUCCESS)
        		{
					do
					{
						c1 = (Customer)objIn.readObject();
						if(c1.getAction() == Customer.LAST_ROW)
						{
							break;
						}
						
						displayArea.append(c1.toString());
						displayArea.append("\n");
					
					}
					while(true);
        		}
			}
        	catch (ClassNotFoundException e1) 
        	{
				e1.printStackTrace();
			} 
        	catch (IOException e1) 
        	{
				e1.printStackTrace();
			}
        }
        
    }




    // empty method for WindowListener interface
	public void windowActivated(WindowEvent arg0) 
	{
		
	}

	// empty method for WindowListener interface
	public void windowClosed(WindowEvent arg0) 
	{
		
	}

	// this method is invoked when the close button is clicked
	public void windowClosing(WindowEvent arg0) 
	{
		if(client != null)
		{
			try
			{
				Customer c = new Customer();
				c.setAction(Customer.END);
				objOut.writeObject(c);
				objOut.flush();
				client.close();
			}
			catch(Exception e)
			{
				
			}
			finally
  {
       try
       {
         if (client != null)
           client.close();
       }
       catch (IOException e2) {}
}
		}
	}

	// empty method for WindowListener interface
	public void windowDeactivated(WindowEvent arg0) 
	{
		
	}
	
	// empty method for WindowListener interface
	public void windowDeiconified(WindowEvent arg0) 
	{
		
	}

	// empty method for WindowListener interface
	public void windowIconified(WindowEvent arg0) 
	{	
		
	}
	
	// empty method for WindowListener interface
	public void windowOpened(WindowEvent arg0) 
	{
		
	}
}

