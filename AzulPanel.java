import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AzulPanel extends JPanel implements ActionListener , MouseListener  {
	private BufferedImage home , board;
	private JButton button1;
	private boolean step1 , step2;	
    public AzulPanel() {
    	try {
    		home = ImageIO.read(new File("AzulHome.png"));
    		board = ImageIO.read(new File("Board.jpg"));
    	}
    	
    	catch (Exception E) {
    		System.out.println("This is a Exception Error" + E.getMessage());
    		return;
    	}
    	
    	button1 = new JButton(" 1 Player ");
    	button1.setSize(100, 80);
    	button1.setLocation(577 , 482);
    	this.setLayout(null);
    	this.add(button1);
    	button1.addActionListener(this);
    	addMouseListener(this);
    	button1.setVisible(true);
    	step1 = true;
    	step2 = false;
    	
     
    	
    	
    }
	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked (MouseEvent e )
	{
	}
        public void paint (Graphics g) {
        			super.paint(g);
        			if (step1 == true) {
        	        g.drawImage(home, 500 , 0 , 450 , 600 , null);
        	        step2 = true;
        	        step1 = false;
        			}
        			
        			else if ( step2 == true) {
        				g.drawImage(board , 0 , 500 , 300 , 450 , null);
        			}
        			
        	        
        }
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == button1) {
				  step2 = true;
			      repaint();
				  
			}
		}
	
}