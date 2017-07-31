import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2012.11.29
 */

public class Triangle extends Shape
{
    private int height;
    private int width;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle()
    {
        super("green", 210, 140);
        height = 60;
        width = 70;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newHeight, int newWidth)
    {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }

    /**
     * Draw the triangle with current specifications on screen.
     */
    protected void draw()
    {
        if(isVisible()) {
            int x = getXPosition();
            int y = getYPosition();
            Canvas canvas = Canvas.getCanvas();
            int[] xpoints = { x, x + (width/2), x - (width/2) };
            int[] ypoints = { y, y + height, y + height };
            canvas.draw(this, getColor(), new Polygon(xpoints, ypoints, 3));
            canvas.wait(10);
        }
    }
}
