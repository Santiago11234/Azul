import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

public class  AzulPanel extends JPanel implements MouseListener {
	
	private BufferedImage board,home;
	
	
	
	public AzulPanel (){ 
	       try {
	        
            board = ImageIO.read(new File("Board.jpg"));
	        home = ImageIO.read(new File("AzulHome.png"));
	       }
	       
	       catch ( Exception E) {
	        System.out.println("This is a Exception Error" + E.getMessage());
	        return;
	       }
	       
	 
	     
	     
	     
	}

	public void paint (Graphics g) {
	      
		// get Random Start Player * This will be in constructor assign it to this int
		
	 // if ( Draw Boards = true ) - draw the fucking boards 
		   // if ( BoardNum == 1 && FactoryT
		g.drawImage(home,0,0,1600,960, null);
        //g.drawImage( board , 500 , 200 , 400 , 400 , null);

	}


	public void mousePressed(MouseEvent e) { }
	public void mouseReleased(MouseEvent e) { }
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked (MouseEvent e )
	{
		int xc = e.getX();
		int yc = e.getY();
		System.out.println("loc is ("+xc+", "+yc+")");
	}


	
	
	
	
	
}
	

