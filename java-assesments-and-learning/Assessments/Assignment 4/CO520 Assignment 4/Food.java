/**
 * Import from Java libraries 
 */
import java.util.ArrayList;

/**
 * The food class, is the object of the food storing the location and 
 * properties of the food. The food is what the snake object eats, 
 * the more food the snake eats the more score the user gets. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class Food implements FoodInterface
{
	/**
	 * Integer variables that store the location and size of the food objects
	 */
	private int posX; // position of the X co-ordinate
	private int posY; // position of the Y co-ordinate 
	private int width; // the width of the food
	private int height; // the height of the food


	/**
	 * Constructor
	 * the food is created with the set size and random location. 
	 * @params: 
	 * Snake body array list, length;
	 * the integer for width w;
	 * the integer for height h; 
	 */
	public Food(ArrayList<SnakeBody> length, int w, int h)
	{
		width = 10;
		height = 10;
		boolean ok = false;
		while(!ok)
		{
			posX = Food.random(0, (w - width));
			posY = Food.random(0, (h - height));

			for(int i=0; i<length.size(); i++)
			{
				ok = ((
				(length.get(i).getPosX() != posX) // gets the position of the snake and checks ... 
				& (length.get(i).getPosY() != posY)) // ... that the food isn't placed on the snake 
				& (((posX%width) == 0) // makes sure that the position isn't null 
				& ((posY%height) == 0)
				));
			}
		}
	}

	/**
	 * Get for the position X 
	 */
	public int getPosX() 
	{ 
		return posX; 
	}
	
	/**
	 * Get for the position Y
	 */
	public int getPosY() 
	{ 
		return posY; 
	}

	/**
	 * Get width -  getWidth
	 */
	public int getWidth()
	{ 
		return width; 
	}
	
	/**
	 * Get Height method 
	 */
	public int getHeight()
	{ 
		return height; 
	}


	/**
	 * random method, to get a random integer, could also use the random library java has
	 */
	static public int random(int a, int b)
	{
		return (int)(Math.random() * b + a);
	}
	
	/**
	 * checks if the snake has eaten
	 */
	public boolean isEaten(Snake snake)
	{
		return ((posX == snake.getBody().get(0).getPosX()) 
				& (posY == snake.getBody().get(0).getPosY()));
	}
}