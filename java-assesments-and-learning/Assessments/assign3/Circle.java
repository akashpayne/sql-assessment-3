import java.awt.*;
import java.awt.geom.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2012.11.29
 */

public class Circle extends Shape
{
    private int diameter;
    
    /**
     * Create a new circle at default position with default color.
     */
    public Circle()
    {
        super("blue", 230, 90);
        diameter = 68;
    }

    /**
     * Change the size to the new size (in pixels). Size must be >= 0.
     */
    public void changeSize(int newDiameter)
    {
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Draw the circle with current specifications on screen.
     */
    protected void draw()
    {
        if(isVisible()) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, getColor(), new Ellipse2D.Double(getXPosition(), getYPosition(), 
                                                          diameter, diameter));
            canvas.wait(10);
        }
    }
}
