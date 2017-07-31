/**
 * This class is the interface for the Snake class. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
public interface SnakeInterface
{
	/**
	 * Returns an ArrayList of SnakeBody to the current Snake object
	 * @return An ArrayList of SnakeBody to the current Snake object
	 * @see SnakeBodyInterface 
	 */
	java.util.ArrayList<SnakeBody> getBody();

	/**
	 * Return the width of the current Snake item
	 * @return The width of the current Snake item
	 */
	int getWidth();

	/**
	 * Return the height of the current Snake item
	 * @return The height of the current Snake item
	 */
	int getHeight();
}