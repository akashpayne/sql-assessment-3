
/**
 * The super class for different shapes.
 * Stores the common aspects of all shapes.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2012.11.29
 */
public abstract class Shape
{
    // Whether the shape is visible.
    private boolean isVisible;
    // The shape's color.
    private String color;
    // The shape's position.
    private int xPosition;
    private int yPosition;

    /**
     * Constructor for objects of class Shape
     * @param color The color of the shape.
     * @param xPosition The initial x position.
     * @param yPosition The initial y position.
     */
    public Shape(String color, int xPosition, int yPosition)
    {
        this.color = color;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        isVisible = false;
    }
    
    /**
     * Return whether the shape is visible or not.
     */
    public boolean isVisible()
    {
        return isVisible;
    }

    /**
     * Make this shape visible. If it was already visible, do nothing.
     */
    public void makeVisible()
    {
        isVisible = true;
        draw();
    }
    
    /**
     * Make this shape invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible()
    {
        erase();
        isVisible = false;
    }
    
    /**
     * Change the color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor)
    {
        color = newColor;
        draw();
    }

    /**
     * Return the color of this shape.
     */
    public String getColor()
    {
        return color;
    }
    
    /**
     * Move the shape a few pixels to the right.
     */
    public void moveRight()
    {
        moveHorizontal(20);
    }

    /**
     * Move the shape a few pixels to the left.
     */
    public void moveLeft()
    {
        moveHorizontal(-20);
    }

    /**
     * Move the shape a few pixels up.
     */
    public void moveUp()
    {
        moveVertical(-20);
    }

    /**
     * Move the shape a few pixels down.
     */
    public void moveDown()
    {
        moveVertical(20);
    }

    /**
     * Move the shape horizontally by 'distance' pixels.
     */
    public void moveHorizontal(int distance)
    {
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the shape vertically by 'distance' pixels.
     */
    public void moveVertical(int distance)
    {
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the shape horizontally by 'distance' pixels.
     */
    public void slowMoveHorizontal(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the shape vertically by 'distance' pixels.
     */
    public void slowMoveVertical(int distance)
    {
        int delta;

        if(distance < 0) 
        {
            delta = -1;
            distance = -distance;
        }
        else 
        {
            delta = 1;
        }

        for(int i = 0; i < distance; i++)
        {
            yPosition += delta;
            draw();
        }
    }

    /**
     * Return the current x position.
     */
    protected int getXPosition()
    {
        return xPosition;
    }
    
    /**
     * Return the current y position.
     */
    protected int getYPosition()
    {
        return yPosition;
    }
    
    /**
     * Draw the shape with current specifications on screen.
     */
    protected abstract void draw();
    
    /**
     * Erase the shape on screen.
     */
    protected void erase()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
}
