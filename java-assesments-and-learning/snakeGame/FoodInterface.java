/**
 * The Interface for the food object
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public interface FoodInterface
{
	/**
		* Returns the current position on X
		* @return Current position on X
	*/
	int getPosX();

	/**
		* Returns the current position on Y
		* @return Current position on Y
	*/
	int getPosY();

	/**
		* Returns the current width
		* @return Current width
	*/
	int getWidth();

	/**
		* Returns the current height
		* @return Current height
	*/
	int getHeight();

	/**
		* boolean isEaten checks if the snake is eating the current object or not.
		* @return A boolean checks if the snake is eating the current object or not.
		* @param snake The Snake object.
	*/
	boolean isEaten(Snake snake);
}