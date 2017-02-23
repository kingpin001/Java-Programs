/*******************************************************************************
assignment-4
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 11/8/2016
********************************************************************************/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class TileDesigner extends JPanel {
	String[] imagename = { "pat1.gif", "pat2.gif", "pat3.gif", "pat4.gif",
			"pat5.gif" };

	
	Image[] imageA = new Image[5];
	JButton pat1;
	JButton pat2;
	JButton pat3;
	JButton pat4;
	JButton pat5;

	
	public TileDesigner() 
        {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.green));

		for (int i = 0; i < imageA.length; i++) {
			imageA[i] = (Image) Toolkit.getDefaultToolkit().getImage(
					imagename[i]);
		}

		
		final TileCanvas centerPanel = new TileCanvas();// creating object for TileCanvas class.
		
		add(centerPanel, BorderLayout.CENTER);// adding centerPanel to outerFrame
		
		centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));//giving border for CenterPanel
		
		centerPanel.ResetGridTile();
		
		// Loading Elements into Image Array
		 
		centerPanel.LoadImageArray();
		centerPanel.CreateMouseListener();
		TileGUI.setCenterPanel(centerPanel);
		
		JPanel southPanel = new JPanel();// declaring and initializing Jpanel
		
		add(southPanel, BorderLayout.SOUTH);// Adding southPanel to JFrame
		
		JButton resetButton = new JButton("Reset");//adding reset button to southPanel
		southPanel.add(resetButton);

		
		resetButton.addActionListener(new ActionListener() //Implementing ActionListener 
                {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.ResetGridTile();
			}
		});

		
		
		  //Declaring and initializing JToolBar and adding button images 
		 
		 
		JToolBar tileToolBar = new JToolBar();
		pat1 = new JButton(new ImageIcon(imageA[0]));
		tileToolBar.add(pat1);

		pat2 = new JButton(new ImageIcon(imageA[1]));
		tileToolBar.add(pat2);

		pat3 = new JButton(new ImageIcon(imageA[2]));
		tileToolBar.add(pat3);

		pat4 = new JButton(new ImageIcon(imageA[3]));
		tileToolBar.add(pat4);

		pat5 = new JButton(new ImageIcon(imageA[4]));
		tileToolBar.add(pat5);

		add(tileToolBar, BorderLayout.NORTH);

		
		pat1.addActionListener(new ActionListener() // Implementing ActionListener for tile buttons.
                {
			public void actionPerformed(ActionEvent e) {
				centerPanel.selectedTile = 0;
			}
		});

		pat2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.selectedTile = 1;
			}
		});

		pat3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.selectedTile = 2;
			}
		});

		pat4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.selectedTile = 3;
			}
		});

		pat5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				centerPanel.selectedTile = 4;
			}
		});

		setVisible(true);
	}
}