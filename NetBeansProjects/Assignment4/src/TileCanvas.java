/*******************************************************************************
assignment-4
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 11/8/2016
********************************************************************************/
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/******************************************************************
 TileCanvas class is the class where we do actual painting. 
 In this class we will draw the rectangle and implement
 action listener for mouse clicks and allows the user to do tile design  
 ******************************************************************/
@SuppressWarnings("serial")
class TileCanvas extends JPanel implements MouseListener
{
	static final int squareSide = 25;							
	int gridRows = 5;          
	int gridCols = 5;          
	int[][] TA = new int[gridRows][gridCols];    
	int startX;          
	int startY;
	int gridWidth;                
	int gridHeight;                
	public int selectedTile = -1;      
	String[] stringImageName = {"pat1.gif","pat2.gif","pat3.gif","pat4.gif","pat5.gif"};
	Image[] imageA = new Image[5];
	
	
	 
	public void ResetGridTile() // This method removes all tiles when reset button is clicked
        {
		for (int row=0; row<gridRows; row++) {
			for (int col=0; col<gridCols; col++) {
				TA[row][col] = -1;
			}
		}
		this.repaint();
	}
	
	
	
	public void LoadImageArray()//this method loads image into array 
        {
		for(int i=0;i<imageA.length;i++) {
			imageA[i] = (Image) Toolkit.getDefaultToolkit().getImage(stringImageName[i]);	
		}
	}
     
	
	
	public void paintComponent(Graphics g) // to build paint component
	{
		super.paintComponent(g);
		gridWidth = gridCols*squareSide;
		gridHeight = gridRows*squareSide;
		int panelWidth = getWidth();            
		int panelHeight = getHeight();           
		startX = (panelWidth-gridWidth)/2;
		startY = (panelHeight-gridHeight)/2;
   
		
		for(int row=0;row<gridRows;row++) {
			for(int col=0;col<gridCols;col++) {
				g.drawRect(startX+(squareSide*col), startY+(squareSide*row), squareSide, squareSide);
			}
		}
     
	
		for(int row=0;row<gridRows;row++) 
		{
			for(int col=0;col<gridCols;col++) 
			{
                            if(TA[col][row] == 0)
                            {
                                g.drawImage(imageA[0], startX+(squareSide*col), startY+(squareSide*row), null);
                            }
                            else if(TA[col][row] == 1)
                            {
                                g.drawImage(imageA[1], startX+(squareSide*col), startY+(squareSide*row), null);
                            }
                            else if(TA[col][row] == 2)
                            {
                                g.drawImage(imageA[2], startX+(squareSide*col), startY+(squareSide*row), null);
                            }
                            else if(TA[col][row] == 3)
                            {
                                g.drawImage(imageA[3], startX+(squareSide*col), startY+(squareSide*row), null);
                            }
                            else if(TA[col][row] == 4)
                            {
                                g.drawImage(imageA[4], startX+(squareSide*col), startY+(squareSide*row), null);
                            }
                            
			}
		}
	}
     
	public void CreateMouseListener() // method to find which rectangle is selected and assign the selected tile against selected tile 
        {
		addMouseListener(this);	
	}
	
	public void mouseClicked(MouseEvent arg0) {
		int x = arg0.getX();
		int y = arg0.getY();
		if(x >= startX && x <= startX+gridWidth && y >= startY && y <= startY+gridWidth) {
			int xIndex = (x-startX)/squareSide;
			int yIndex = (y-startY)/squareSide;
			TA[xIndex][yIndex] = selectedTile;
			this.repaint();
		}
	}

        
        
        //Do Nothing methods: In order to implement MouseListener we have to override all these functions. This program doesn't require this event to be handled.
	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	public void mouseExited(MouseEvent arg0) 
	{
		
	}

	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	public void mouseReleased(MouseEvent arg0) 
	{
		
	}
	
}