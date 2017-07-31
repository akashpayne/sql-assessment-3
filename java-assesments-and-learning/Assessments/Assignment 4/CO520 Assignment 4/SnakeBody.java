/**
 * The SnakeBody class stores the information about the snake's body, 
 * i.e. the position and direction.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */
 
/**
 * Direction (enum below) - could be made into a separate class 
 */
enum Direction{NORTH, SOUTH, EAST, WEST};

public class SnakeBody implements SnakeBodyInterface
{
	/**
	 * Variables used to store the snake's position and direction
	 */
	private int posX;
	private int posY;
	private Direction direction;


	/**
	 * Initialises the snake's body's position and direction.
	 */
	public SnakeBody(int x, int y, Direction d)
	{
		posX = x;
		posY = y;
		direction = d;
	}


	/**
	 * gets the x position
	 */
	public int getPosX()
	{ 
		return posX; 
	}
	
	/**
	 * gets the y position
	 */
	public int getPosY()
	{ 
		return posY; 
	}
	
	/**
	 * gets the direction 
	 */
	public Direction getDirection()
	{ 
		return direction;
	}
	
	/**
	 * sets the x position
	 */
	public void setPosX(int x)
	{ 
		posX = x;
	}
	
	/**
	 * sets the Y position
	 */
	public void setPosY(int y)
	{ 
		posY = y;
	}
	
	/**
	 * sets the direction 
	 */
	public void setDirection(Direction d)
	{ 
		direction = d;
	}


	/**
	 * the equals method tests whether the Snake's body x and y positions are equal to 
	 * another snake body part.
	 */
	public boolean equals(SnakeBody other)
	{
		return ((posX == other.getPosX()) & (posY == other.getPosY()));
	}
}