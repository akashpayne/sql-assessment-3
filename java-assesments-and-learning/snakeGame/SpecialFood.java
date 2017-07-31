/**
 * Import from Java libraries 
 */
import java.util.ArrayList;

/**
 * This class is used to hold the object for the special food, i.e.
 * an object that gives the user more score. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

 public class SpecialFood extends Food implements SpecialFoodInterface
{
	/**
	 * Variables used by the special food object
	 */
	private int timeToLive;
	private boolean isPainted;

	/**
	 * The Position of the Special food 
	 */
	public SpecialFood(ArrayList<SnakeBody> L, int x, int y)
	{
		super(L, x, y);
		timeToLive = 150;
	}

	/**
	 * Gets the time of the special food
	 */
	public int getTimeToLive() 
	{ 
		return timeToLive; 
	}

	/**
	 * decreases the time that the special food has
	 */
	public void decreaseTime()
	{
		timeToLive = timeToLive - 1;
	}
}