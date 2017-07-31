/**
 * Import from Java libraries 
 */
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import java.io.File;
import java.io.IOException;

/**
 * The drawing panel class, that extends JPanel and 
 * implements ActionListener and the interface for the drawing panel,
 * is used to set the timer, draw the game (with a possible custom 
 * background), 
 * 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
public class DrawingPanel extends JPanel implements ActionListener, DrawingPanelInterface
{	
	/**
	 * These variables store the values for the board size, background image,
	 * and the objects used for the game. 
	 */
	private int backWidth;
	private int backHeight;
	private Image backgroundImage;
	private Snake snake;
	private Food food;
	private Timer timer;
	private SpecialFood specialFood;
	private int specialFoodTimer;

	/**
	 * The Drawing Panel (url used for the image in the background if any)
	 * is used to initialise the game by a thread and draw or paint the
	 * game together. 
	 */
	public DrawingPanel(String url)
	{
		super();
		timer = new Timer(100, this);
		setDoubleBuffered(true);
		try
		{
			backgroundImage = ImageIO.read(new File(url)); 
			// haven't got a picture but feature is there, user can enter one
			backWidth = backgroundImage.getWidth(this);
			backHeight = backgroundImage.getHeight(this);
			System.out.println("Your custom background picture has been loaded"); 
			// highlights this feature to a user
		}
		catch(IOException e)
		{
			backgroundImageMessage();
			backgroundImage = null;
			backWidth = 640;
			backHeight = 400;
		}
		snake = new Snake();
		food = new Food(snake.getBody(), backWidth, backHeight);
		specialFoodTimer = 15;
		specialFood = null;
	}
	
	/**
	 * Message given when the user plays the game without a Image as a background.
	 */
	public void backgroundImageMessage()
	{
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
		System.out.println("* The default background will be used.      *");
		System.out.println("* (Note: CAN give a parameter that holds:   *");
		System.out.println("*  the path of the background picture ...   *");
		System.out.println("*  .. that you wish to use during the game) *");
		System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
	}
	
	/**
	 * This method gets the back width of the JPanel.
	 */
	public int getBackWidth() 
	{
		return backWidth; 
	}
	
	/**
	 * This method gets the back height of the JPanel.
	 */
	public int getBackHeight()
	{
		return backHeight; 
	}

	/**
	 * This method gets the timer object of the Timer library.
	 */
	public Timer getTimer()
	{
		return timer; 
	}

	/**
	 * This method gets the snake object from the snake class. 
	 */
	public Snake getSnake()
	{
		return snake; 
	}

	/**
	 * This method runs the thread that holds the game.
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == timer)
		{
			try
			{
				move();
			}
			catch(BitInitialiseException expt)
			{
				repaint();
				JOptionPane.showMessageDialog(this, expt.getMessage(), "Snake", JOptionPane.ERROR_MESSAGE);
				Game.score.updateHighScore(Game.score.getCurrentScore());
				try
				{
					Thread.sleep(200);
					System.exit(0);
				}
				catch(Exception expt2)
				{
					System.out.println(expt2.getMessage());
				}
			}
		}
	}

	/**
	 * This method paints the board that backs the game.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.BLUE);
		g.drawRect(0, 0, getWidth(), getHeight());

		if(backgroundImage != null)
		{
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
		snake.drawSnake(g); // paints the snake
		g.setColor(Color.RED); // paints the food- Oval shape
		g.fillOval(food.getPosX(), food.getPosY(), food.getWidth(), food.getHeight()); 

		if(specialFood != null)
		{
			g.setColor(Color.RED); // paints the food- Oval shape
			g.fillOval(specialFood.getPosX(), specialFood.getPosY(), specialFood.getWidth(), specialFood.getHeight());
		}
	}

	/**
	 * This method updates the position of the snake 
	 * The method checks if the snake is in contact with the wall using the directions enum.
	 * The method also uses BorderException to stop game errors.
	 */
	public void updatePosition(Snake snake) throws BorderException
	{
		int new_pos;
		for(int i=0; i<snake.getBody().size(); i++) // checks if the snake has hit a wall
		{
			if(snake.getBody().get(i).getDirection() == Direction.NORTH)
			{
				new_pos = snake.getBody().get(i).getPosY() - snake.getHeight();

				if(new_pos < 0)
				{
					throw new BorderException("GAME OVER!!\n You have hit a wall!");
				}
				else
				{
					snake.getBody().get(i).setPosY(new_pos);
				}
			}
			else if(snake.getBody().get(i).getDirection() == Direction.SOUTH)
			{
				new_pos = snake.getBody().get(i).getPosY() + snake.getHeight();

				if(new_pos >= getHeight())
				{
					throw new BorderException("GAME OVER!!\n You have hit a wall!");
				}
				else
				{
					snake.getBody().get(i).setPosY(new_pos);	
				}
			}
			else if(snake.getBody().get(i).getDirection() == Direction.WEST)
			{
				new_pos = snake.getBody().get(i).getPosX() - snake.getWidth();

				if(new_pos < 0)
				{
					throw new BorderException("GAME OVER!!\n You have hit a wall!");
				}
				else
				{
					snake.getBody().get(i).setPosX(new_pos);
				}
			}
			else if(snake.getBody().get(i).getDirection() == Direction.EAST)
			{
				new_pos = snake.getBody().get(i).getPosX() + snake.getWidth();

				if(new_pos >= getWidth())
				{
					throw new BorderException("GAME OVER!!\n You have hit a wall!");
				}
				else
				{
					snake.getBody().get(i).setPosX(new_pos);
				}
			}
		}
		snake.updateDirections();
	}

	/**
	 * This method moves the snake around the board.  
	 * The method updates the score, deletes the food,
	 * adds new food and increase the length of the 
	 * snake.
	 * The method also uses BitInitialiseException to
	 * stop game errors. 
	 */
	public void move() throws BitInitialiseException 
	{
		Game.listen_keyboard = false;
		if(food.isEaten(snake))
		{
			Game.score.incrementsCurrentScore(false);
			specialFoodTimer = specialFoodTimer - 1;
			food = null;
			snake.getLonger();
			food = new Food(snake.getBody(), getWidth(), getHeight());
		}
		if(specialFood == null)
		{
			if(specialFoodTimer <= 0)
			{
				specialFood = new SpecialFood(snake.getBody(), getWidth(), getHeight());
			}
		}
		else
		{
			if(specialFood.getTimeToLive() > 0)
			{
				specialFood.decreaseTime();

				if(specialFood.isEaten(snake))
				{
					Game.score.incrementsCurrentScore(true);
					specialFood = null;
					specialFoodTimer = 15;
				}
			}
			else
			{
				specialFood = null;
				specialFoodTimer = 15;
			}
		}
		try
		{
			updatePosition(snake);
		}
		catch(BorderException e)
		{
			repaint();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Snake", JOptionPane.ERROR_MESSAGE);
			Game.score.updateHighScore(Game.score.getCurrentScore());
			try
			{
				Thread.sleep(200);
			}
			catch(Exception e2)
			{
				System.out.println(e2.getMessage());
			}
			System.exit(0);
		}
		for(int i=1; i<snake.getBody().size(); i++)
		{
			if(snake.getBody().get(0).equals(snake.getBody().get(i)))
			{
				throw new BitInitialiseException("GAME OVER!! \n You have eaten your own tail.");
			}
		}
		repaint();
		Game.listen_keyboard = true;
	}
}