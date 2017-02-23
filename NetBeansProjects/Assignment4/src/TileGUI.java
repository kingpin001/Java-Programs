/*******************************************************************************
assignment-4
csci 680
team members: kavya ampani (z1773728)
              sri venkat manchikalapudi(Z1781696)
due date: 11/8/2016
********************************************************************************/

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class TileGUI extends JFrame implements KeyListener {
	static TileCanvas cPanel;

	public static void main(String[] args) 
        {
		SwingUtilities.invokeLater(new Runnable() 
                {
			public void run() 
                        {
				createAndShowGUI();
			}
		});
	}

	public static void setCenterPanel(TileCanvas centerPanel) 
        {
		cPanel = centerPanel;
	}
	
	public static void createAndShowGUI() 
        {
		/********************************
		 Creating and setting up the window.
		 *********************************/
		final TileGUI frame = new TileGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setName("Tile Designer");
		frame.setSize(500, 500);

		JMenuBar menubar = new JMenuBar();
		JMenu file = new JMenu("Menu");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem help = new JMenuItem("Help");
		file.add(reset);
		file.add(help);

		reset.addActionListener(new ActionListener() 
                {
			
			@Override
			public void actionPerformed(ActionEvent arg0) 
                        {
				// To create Auto-generated method stub
				cPanel.ResetGridTile();
			}
		}
                );
		
		menubar.add(file);
		help.addActionListener(new ActionListener() 
                {

			@Override
			public void actionPerformed(ActionEvent arg0)
                        {
				//To create Auto-generated method stub
				JOptionPane.showMessageDialog(frame, "useful help here");
			}
		}
                );

		frame.setJMenuBar(menubar);
	
		frame.setVisible(true);
	}

	public TileGUI() 
        {
		super();
		setFocusable(true);
		addKeyListener(this);
		TileDesigner tiledesign = new TileDesigner();
		setLayout(new GridLayout(1, 2));
		add(tiledesign);
	}

	private final Set<Character> P = new HashSet<>();	
	@Override
	public void keyTyped(KeyEvent e) 
        {
		// To create Auto-generated method stub
	}
	
	@Override
	public void keyReleased(KeyEvent e) 
        {
		// To create Auto-generated method stub
		P.remove(e.getKeyChar());
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
        {
		System.out.println("hello");
		// To create Auto-generated method stub
		P.add(e.getKeyChar());
		if(P.size() > 1) 
                {
			if((e.getKeyCode() == KeyEvent.VK_CONTROL) && (e.getKeyCode() == KeyEvent.VK_R)) 
                        {
				cPanel.ResetGridTile();
			}
		}
	}
}