/**
 * Import from Java libraries 
 */
 import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.*;

/**
 * Snake class controls the snake that the user moves. 
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class Snake implements SnakeInterface
{
	/**
	 * Snake size and length variables 
	 */
	private ArrayList<SnakeBody> body;
	private int width;
	private int height;


	/**
	 * The constructor initialises the snake with default values 
	 */
	public Snake()
	{	
		width = 10;
		height = 10;

		body = new ArrayList<SnakeBody>();
		for(int i=0; i<=(width * 4); i = i + width)
		{
			body.add(new SnakeBody(((width * 4) - i), 0, Direction.EAST));
		}
	}


	/**
	 * gets the array for the snake's body
	 */
	public ArrayList<SnakeBody> getBody() 
	{ 
		return body; 
	}
	
	/**
	 * gets the width 
	 */
	public int getWidth() 
	{ 
		return width; 
	}

	/**
	 * gets the height
	 */
	public int getHeight() 
	{ 
		return height;
	}

	/**
	 * the draw snake method draws the snake onto the application
	 */
	public void drawSnake(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(body.get(0).getPosX(), body.get(0).getPosY(), width, height);

		g.setColor(Color.GREEN);
		g.drawRect(body.get(0).getPosX(), body.get(0).getPosY(), width, height);

		g.setColor(Color.GREEN);
		for(int i=1; i<body.size(); i++)
		{
			g.fillRect(body.get(i).getPosX(), body.get(i).getPosY(), width, height);
		}
	}
	
	/**
	 * the update direction method sets the possible directions 
	 */
	public void updateDirections()
	{
		for(int i = (body.size() - 1); i > 0; i--)
		{
			body.get(i).setDirection(body.get(i-1).getDirection());
		}
	}

	/**
	 * the get longer method increases the snakes body size
	 */
	public void getLonger()
	{
		int i = body.size() - 1;

		if(body.get(i).getDirection() == Direction.NORTH)
		{
			body.add(new SnakeBody(body.get(i).getPosX(), (body.get(i).getPosY() + height), Direction.NORTH));
		}
		else if(body.get(i).getDirection() == Direction.SOUTH)
		{
			body.add(new SnakeBody(body.get(i).getPosX(), (body.get(i).getPosY() - height), Direction.SOUTH));
		}
		else if(body.get(i).getDirection() == Direction.WEST)
		{
			body.add(new SnakeBody((body.get(i).getPosX() + width), body.get(i).getPosY(), Direction.WEST));
		}
		else if(body.get(i).getDirection() == Direction.EAST)
		{
			body.add(new SnakeBody((body.get(i).getPosX() - width), body.get(i).getPosY(), Direction.EAST));
		}
	}
}