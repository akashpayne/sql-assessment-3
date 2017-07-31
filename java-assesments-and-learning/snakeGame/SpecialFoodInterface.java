/**
 * This class is the Interface for the special food class. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 public interface SpecialFoodInterface extends FoodInterface
{
	/**
	 * Returns the time that the special food will be kept displayed on the screen
	 * @return The time that the special food will be kept displayed on the screen
	 */
	int getTimeToLive();

	/**
	 * Decreases the time that the food is being displayed on the screen
	 */
	void decreaseTime();
}