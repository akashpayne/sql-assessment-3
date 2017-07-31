/**
 * This class is the interface for the Score Class.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public interface ScoresInterface
{
	/**
	 * Returns the score of the current game
	 * @return The score of the current game
	 */
	int getCurrentScore();

	/**
	 * Returns the highest score recorded
	 * @return The highest score recorded
	 */
	int getHighScore();

	/**
	 * Increments the score of the current game.
	 * Depends of the parameter.
	 * @param specialFood A boolean which tells if the Food 
	 *        eaten by the snake is a SpecialFoodor not.
	 * @see Snake
	 * @see Food
	 * @see SpecialFood
	 */
	void incrementsCurrentScore(boolean specialFood);

	/**
	 * Updates the highest score recorded, if it needs to be done
	 * @param int s = The score done by the player at the last game. 
	 *        It will be compared to the current highest score 
	 *        recorded and replace if it's greater.
	 */
	void updateHighScore(int s);
}