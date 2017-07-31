import java.util.Set;
import java.util.HashSet;

/**
 * Class for the single player in the game of Zuul.
 * Keeps track of all its state.
 * 
 * @author Olaf Chitil
 * @version 28/2/2014
 */
public class Player
{
    private Room currentRoom;
    private Set<Item> items;
    
    /**
     * Constructor for an object of class Player
     */
    public Player(Room current)
    {
        currentRoom = current;
        items = new HashSet<Item>();
    }

    /**
     * Set the current room the player is in.
     * 
     * @param  current  The new current room
     */
    public void setCurrentRoom(Room current)
    {
        currentRoom = current;
    }
    
    /**
     * Get the current room the player is in.
     * 
     * @return The current room
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Take a given item from the current room.
     * If the item is not in the current room, then do nothing.
     * @return  true, if the item was in the current room
     */
    public boolean take(Item item)
    {
        boolean success = currentRoom.remove(item);
        if (success) {
            items.add(item);
        }
        return success;
    }
    
    /**
     * Drop a given item in the current room.
     * If the item is not currently held by the player, then do nothing.
     * @return  true, if the item was held by the player
     */
    public boolean drop(Item item)
    {
        boolean success = items.remove(item);
        if (success) {
            currentRoom.add(item);
        }
        return success;
    }
    
    /**
     * Get strength of player. Based on all the items the player holds.
     */
    public int getStrength()
    {
        int s = 0;
        for (Item i : items) {
            s += i.getStrength();
        }
        return s;
    }
}
