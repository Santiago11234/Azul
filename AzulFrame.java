


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class AzulFrame extends JFrame {
	private static final int WIDTH = 1600;
	private static final int HEIGHT = 960;
	
	public AzulFrame (String title) {
			super(title);
			setSize(WIDTH,HEIGHT);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add( new AzulPanel());
			setVisible(true);
			
	}



	//@Override
	//public void actionPerformed(ActionEvent e) {
		//// TODO Auto-generated method stub
		
	//}
}



