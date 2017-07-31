import java.awt.*;

/**
 * A square that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2012.11.29
 */

public class Square extends Shape
{
    // The square's width/height.
    private int size;

    /**
     * Create a new square at default position with default color.
     */
    public Square()
    {
        super("red", 310, 120);
        size = 60;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newSize)
    {
        erase();
        size = newSize;
        draw();
    }

    /**
     * Draw the square with current specifications on screen.
     */
    protected void draw()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(),
                        new Rectangle(getXPosition(), getYPosition(), size, size));
            canvas.wait(10);
        }
    }
}
