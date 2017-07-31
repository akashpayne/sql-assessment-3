import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

/**
 * 
 * @author Akash Payne
 *
 */
public class Frame extends JFrame{
	private Container container;
	private DBConnect database;
	
	public Frame(String title, DBConnect database){
		super(title);
		this.database = database;
		buildFrame();
		setVisible(true);
	}

	private void buildFrame(){
		container = getContentPane();
		container.setBackground(Color.YELLOW);
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		new Login(container, this, database);
	}
}
