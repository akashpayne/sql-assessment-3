/**
 * Import from Java libraries 
 */
import java.awt.Font;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;

/**
 * Snake is a retro/classic game in which the player controls a snake that moves around and eats. 
 * With each item that the snake eats, it grows in length by one, and the player earns one point. 
 * The player loses the game when the snake either collides with it's tail, or a wall.
 * 
 * @author (Akash Payne) ap567
 * @version 31/03/2014
 */

public class Main
{
    public static void main(String[] args)
    {
        UIManager.put("OptionPane.messageFont", new FontUIResource(
                         new Font("Lucida Console", Font.PLAIN, 14)));
        
        if(args.length <=0)
        {
            new Game("");
        }
        else
        {
            new Game(args[0]);
        }

        System.gc();
    }
}