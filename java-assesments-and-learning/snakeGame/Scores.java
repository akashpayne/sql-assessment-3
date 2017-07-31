/**
 * Import from Java libraries 
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridLayout;

/**
 * This class holds the characteristics for the score system.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class Scores extends JPanel implements ScoresInterface
{	
	/**
	 * The variables used by the scores class;
	 * The scores, current and high score;
	 * The JLabels used to display the scores.
	 */
	private int currentScore;
	private int highScore;
	private JLabel currentScoreDisplay;
	private JLabel highScoreDisplay;

	/**
	 *  The get method for the current score.
	 */
	public int getCurrentScore()
	{
		return currentScore;
	}

	/**
	 * The get method for the highest score.
	 */
	public int getHighScore() 
	{ 
		return highScore; 
	}

	/**
	 *  The score method that creates a text file that stores the highest score.
	 */
	public Scores()
	{
		super();
		currentScore = 0;
		try
		{
			FileReader file = new FileReader("high_score.txt");
			if(file.ready())
			{
				char char_read;
				StringBuffer s = new StringBuffer();
				while(file.ready())
				{
					char_read = (char)file.read();

					if(char_read != '\n' && char_read != ' ')
					{
						s.append(char_read);
					}
				}
				highScore = Integer.parseInt(s.toString());
			}
			else
			{
				highScore = 0;
			}
			file.close();
		}
		catch(FileNotFoundException e)
		{
			highScore = 0;
		}
		catch(IOException e)
		{
			highScore = 0;
		}

		currentScoreDisplay = new JLabel("Current Score: "+Integer.toString(currentScore));
		highScoreDisplay = new JLabel("HighScore: "+Integer.toString(highScore));

		this.setLayout(new GridLayout(2,1));
		this.add(currentScoreDisplay);
		this.add(highScoreDisplay);
	}

	/**
	 * This method increases the current score as the user eats food. 
	 */
	public void incrementsCurrentScore(boolean specialFood)
	{
		if(specialFood)
		{
			if(Preferences.getDifficulty() == 50)
			{
				currentScore = currentScore + 50;
			}
			else if(Preferences.getDifficulty() == 100)
			{
				currentScore = currentScore + 25;
			}
			else if(Preferences.getDifficulty() == 150)
			{
				currentScore = currentScore + 10;
			}
			currentScoreDisplay.setText("Current score: "+Integer.toString(currentScore));
		}
		else
		{
			if(Preferences.getDifficulty() == 50)
			{
				currentScore = currentScore + 10;
			}
			else if(Preferences.getDifficulty() == 100)
			{
				currentScore = currentScore + 5;
			}
			else if(Preferences.getDifficulty() == 150)
			{
				currentScore = currentScore + 2;
			}
			currentScoreDisplay.setText("Current score: "+Integer.toString(currentScore));
		}
	}

	/**
	 * This method updates the highscore in the text file.
	 */
	public void updateHighScore(int s)
	{
		if(s > highScore)
		{
			highScore = s;
			try
			{
				FileWriter file = new FileWriter("high_score.txt");
				file.write(Integer.toString(highScore));

				file.close();
			}
			catch(FileNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
}