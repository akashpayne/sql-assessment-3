import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 28/1/2015
 * 
 * @editor william Higgins
 * @12/02/2015
 * 
 * 
 * 
 * 
 */

public class Room 
{
    private String description;
    private HashMap<Direction, Room> exits;        // stores exits of this room.
    private HashMap<Item, Room> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * Pre-condition: description is not null.
     */
    public Room(String description) 
    {
        assert description != null : "Room.Room has null description";
        this.description = description;
        exits = new HashMap<Direction, Room>();
        items = new HashMap<Item, Room>();
        sane();
    }

    /**
     * Class invariant: getShortDescription() and getLongDescription() don't return null.
     */
    public void sane()
    {
        assert getShortDescription() != null : "Room has no short description" ;
        assert getLongDescription() != null : "Room has no long description" ;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     * Pre-condition: neither direction nor neighbor are null; 
     * there is no room in given direction yet.
     */
    public void setExit(Direction direction, Room neighbor) 
    {
        assert direction != null : "Room.setExit gets null direction";
        assert neighbor != null : "Room.setExit gets null neighbor";
        assert getExit(direction) == null : "Room.setExit set for direction that has neighbor";
        sane();
        exits.put(direction, neighbor);
        sane();
        assert getExit(direction) == neighbor : "Room.setExit has wrong neighbor";
    }

    public void setItem(Item item, Room room)
    {
        assert item != null : "Room.setItem gets null item";
        assert room != null : "Room.setItem gets null room";
        assert getItem(item) == null : "Room.setItem set for item has item";
        sane();
        items.put(item, room);
        sane();
        assert getItem(item) == room : "Room.setItem has wrong item";
    }

    public void removeItem(Item item, Room room)
    {
       // System.out.println("before remove ");
       // System.out.println(items);
        items.remove(item, room);
       // System.out.println("after remove ");
       // System.out.println(items);
    }

    public String toString()
    {
        return description;
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n" + getAllItemsString();
    }

    public HashMap<Item, Room> getItems()
    {
        return items;
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits: ";
        Set<Direction> keys = exits.keySet();
        for(Direction exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public String getAllItemsString()
    {
        String returnString = "Items in room: ";
        Set<Item> keys = items.keySet();
        for (Item items : keys)
        {
            returnString += " " + items + " , weight is: " + items.getWeight();
        }
        return returnString;
    }    

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     * Pre-condition: direction is not null
     */
    public Room getExit(Direction direction) 
    {
        assert direction != null : "Room.getExit has null direction";
        sane();
        return exits.get(direction);
    }

    public Room getItem(Item item)
    {
        assert item != null : "Room.getItem Has null item";     
        sane();
        // System.out.println("before get ");
        // System.out.println(items);
        return items.get(item);

    }
    
    

    public int getWeight(Item item)
    {
        return item.getWeight();
    }
}

