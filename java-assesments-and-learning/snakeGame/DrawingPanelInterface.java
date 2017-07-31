/**
 * Import from Java libraries 
 */
import javax.swing.Timer;

/**
 * This method is the interface for the DrawingPanel Class.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
public interface DrawingPanelInterface
{
	/**
	 * Returns the current size (width) of the panel
	 * @return Current width of the panel
	 */
	int getBackWidth();

	/**
	 * Returns the current size (height) of the panel
	 * @return Current height of the panel
	 */
	int getBackHeight();

	/**
	 * Returns the Timer object in it current state. 
	 * This object will be used to run or pause/stop the program's execution.
	 * @return Timer Object
	 */
	Timer getTimer();


	/**
	 * Returns the Snake object in it current state. 
	 * This object contains all of the snake's characteristics.
	 * @return Snake Object	
	 */
	Snake getSnake();

	/**
	 * This methods is used to update the Snake's position each time the timer wake up the current process.
	 * It only updates the snake's position, and not it directions.
     * This is updated by a method from the Snake class called updateDirections()
	 * @param snake that this method updates it position
	 * @exception BorderException If the snake's new position is in contact with a wall - gives error (excepted)
     * @see SnakeInterface
	 */
	void updatePosition(Snake snake) throws BorderException;

	/**
	 * This method checks if the snake is eating a food at the moment it's called.
	 * This method is the one called by the main process, and 
	 * calls the method updatePosition(Snake). 
	 * It also repaints the graphics each time it's called.
	 * @exception BitInitialiseException If the snake is eating it own tail - gives error (excepted)
	 */
	void move() throws BitInitialiseException;
}