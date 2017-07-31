/**
 * Import from Java libraries 
 */
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import javax.swing.*;

/**
 * The preferences class stores and allows the user to choose different options 
 * that either change the game play to suit themselves or the game interface to
 * make the game suit their needs. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class ShowInformation extends JFrame implements MouseListener
{
    /**
     * Variables for the buttons and labels 
     */
    private JLabel aboutLabel;
    private JLabel controlLabel;
    private JLabel informationLabel;
    private JButton validationButton;

    /**
     * The Constructor initialises the new JFrame and allows the user to choose their options
     * the frame uses different types of buttons to give the user more flexibility and an easier
     * interface. 
     */
    public ShowInformation()
    {
        super();
        setSize(400,400);
        setTitle("About");
        setLocationRelativeTo(null);

        aboutLabel = new JLabel(showAboutAuthor()); 
        controlLabel = new JLabel(showControls()); 
        informationLabel = new JLabel(showInstructions()); 
        
        validationButton = new JButton("Continue"); // Continue button
        validationButton.addMouseListener(this);

        getContentPane().setLayout(new GridBagLayout()); // the layout 
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(aboutLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        getContentPane().add(controlLabel, constraints);

		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(20, 0, 0, 0);
		getContentPane().add(validationButton, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
        setVisible(true);
    }
    
    /**
     * Shows the about author message.
     */
    public String showAboutAuthor()
    {
        String about = "*************** Author ***************\n";
            about += " Author: Akash Payne (ap567) \n";
            about += " Version: Snake 1.0 \n  Date: 31/03/2014 \n";             
        return about;
    }
    
    /**
     * Controls for the game.
     */
    public String showControls()
    {
        String controls = "*************** Controls ***************\n";
            controls += "      ~ escape : exit the application    \n";     
            controls += "      ~ space  : pause the application   \n"; 
            controls += "      ~ up     : move the snake up        \n"; 
            controls += "      ~ down   : move the snake down      \n"; 
            controls += "      ~ left   : move the snake left      \n"; 
            controls += "      ~ right  : move the snake right     \n"; 
            controls += " ***************          *************** \n";
        return controls;
    }
    
    /**
     * Shows the user the Instructions.
     */
    public String showInstructions()
    {
        String rules = "***************Game Instructions***************\n";
            rules += "1. Select your game mode,\n  and be careful of the borders!\n";
            rules += "2. Collect food to increase size,\n   and to score points.\n";
            rules += "3. Try not to run into your own snake body,\n  as you will eat yourself and die!\n";
            rules += "4. Enjoy!!!!";
        return rules;
    }
    /**
	 * This method checks what the user has clicked on and sets the difficulty to that 
	 * value.
	 */
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() == validationButton)
		{
		}
		dispose();
	}

	public void mouseEntered(MouseEvent e)
	{	
	}

	public void mouseExited(MouseEvent e)
	{	
	}

	public void mousePressed(MouseEvent e)
	{	
	}

	public void mouseReleased(MouseEvent e)
	{	
	}
}