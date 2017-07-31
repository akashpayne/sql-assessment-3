/**
 * This class is the interface for the snakebody class.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
 public interface SnakeBodyInterface
{
	/**
	 * Returns the current position of this SnakeBody of the game's display, on X
	 * @return The current position of this SnakeBody of the game's display, on X
	 */
	int getPosX();

	/**
	 * Returns the current position of this SnakeBody of the game's display, on Y
	 * @return The current position of this SnakeBody of the game's display, on Y
	 */
	int getPosY();

	/**
	 * Returns the current direction where this SnakeBody is moving to
	 * @return The current direction where this SnakeBody is moving to
	 */
	Direction getDirection();

	/**
	 * Set the position of this SnakeBody of the game's display, on X
	 */
	void setPosX(int x);

	/**
	 * Set the position of this SnakeBody of the game's display, on Y
	 */
	void setPosY(int y);

	/**
	 * Set the direction where this SnakeBody is moving to
	 */
	void setDirection(Direction d);

	/**
	 * The Object equal method
	 * @param other The new SnakeBody will be compared to the current SnakeBody
	 * @return True is this SnakeBody is the same as the one given as a parameter
	 */
	boolean equals(SnakeBody other);
}