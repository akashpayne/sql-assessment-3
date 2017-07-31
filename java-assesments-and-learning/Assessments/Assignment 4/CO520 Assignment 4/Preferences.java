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

public class Preferences extends JFrame implements MouseListener
{
	/**
	 * Variables for the buttons and labels 
	 */
	private JLabel difficultyLabel;
	private ButtonGroup difficultySelection;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	private JButton validationButton;
	/**
	 * Variable to set the difficult
	 */
	static private int difficulty = 100;

	/**
	 * The Constructor initialises the new JFrame and allows the user to choose their options
	 * the frame uses different types of buttons to give the user more flexibility and an easier
	 * interface. 
	 */
	public Preferences()
	{
		super();

		setSize(400,200);
		setTitle("Settings");
		setLocationRelativeTo(null);

		difficultyLabel = new JLabel("Please set the difficulty:"); // Difficulty
		easy = new JRadioButton("Easy", false);
		medium = new JRadioButton("Medium", true);
		hard = new JRadioButton("Hard",false);

		validationButton = new JButton("Submit"); // Submit button
		validationButton.addMouseListener(this);

		difficultySelection = new ButtonGroup(); // group the buttons
		difficultySelection.add(easy);
		difficultySelection.add(medium);
		difficultySelection.add(hard);

		getContentPane().setLayout(new GridBagLayout()); // the layout 
		GridBagConstraints constraints = new GridBagConstraints();

		constraints.gridx = 0; // the locations
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, 0, 10);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(difficultyLabel, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);

		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(easy, constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(medium, constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(hard, constraints);

		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(20, 0, 0, 0);
		getContentPane().add(validationButton, constraints);

		constraints.insets = new Insets(0, 0, 0, 0);
		
		setVisible(true);
	}

	/**
	 * This method gets the difficulty set
	 */
	static public int getDifficulty()
	{ 
		return difficulty; 
	}

	/**
	 * This method checks what the user has clicked on and sets the difficulty to that 
	 * value.
	 */
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource() == validationButton)
		{
			Enumeration<AbstractButton> elements = difficultySelection.getElements();
			boolean found = false;
			while(elements.hasMoreElements() & !found)
			{
				AbstractButton b = (AbstractButton) elements.nextElement();
				if(b.isSelected())
				{
					if(b == easy)
					{
						difficulty = 150;
					}
					else if(b == medium)
					{
						difficulty = 100;
					}
					else if(b == hard)
					{
						difficulty = 50;
					}
					found = true;
				}
			}
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