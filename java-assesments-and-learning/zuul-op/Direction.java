
/**
 * This is the direction enumeration class
 * this makes sure that that strings for directions are avoided as far as possible
 * 
 * @author William Higgins 
 * @version 12/02/2015
 */
public enum Direction
{
    
    NORTH("north"), SOUTH("south"), EAST("east"), WEST("west"), UP("up"), DOWN("down");
    
    private String commandString;
    
    Direction(String commandString)
    {
        this.commandString = commandString;    
    }
    
    public String toString()
    {
        return commandString;
    }
}
