import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

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
 * A Room may also contain Items and Characters.
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 16/3/2014
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Set<Item> items;
    private Set<Character> characters;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new HashSet<Item>();
        characters = new HashSet<Character>();
    }

    /** 
     * Add an item to the room.
     * @param i  The item to be added.
     */
    public void add(Item i)
    {
        items.add(i);
    }
    
    /**
     * Remove an item from the room.
     * If the item is not in the room, then do nothing.
     * @param i  The item to be removed.
     * @return  true, if the item was in the room
     */
    public boolean remove(Item i)
    {
        return (items.remove(i));
    }
    
    /**
     * Return a string describing the room's items, started by a new line, for example
     * "\nItems: sword key".
     * The string is empty if the room holds no items.
     * @return Details of the room's items.
     */
    private String getItemString()
    {
        if (items.isEmpty()) {
            return "";
        }
        
        String returnString = "\nItems:";
        for(Item item : items) {
            returnString += " " + item.toString();
        }
        return returnString;
    }
    
    /** 
     * Add a character to the room.
     * @param c  The character to be added.
     */
    public void add(Character c)
    {
        characters.add(c);
    }
    
    /**
     * Remove a character from the room.
     * If the character is not in the room, then do nothing.
     * @param c  The character to be removed.
     * @return  true, if the character was in the room
     */
    public boolean remove(Character c)
    {
        return (characters.remove(c));
    }
    
    /**
     * Return a string describing the room's characters, started by a new line, for example
     * "\nCharacters: dwarf dragon".
     * The string is empty if the room holds no characters.
     * @return Details of the room's characters.
     */
    private String getCharacterString()
    {
        if (characters.isEmpty()) {
            return "";
        }
        
        String returnString = "\nCharacters:";
        for(Character character : characters) {
            returnString += " " + character.toString();
        }
        return returnString;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
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
        return "You are " + description + ".\n" + getExitString() + getItemString() + getCharacterString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Get a random neighbour of this room.
     * Null if there is no neighbour.
     * @return The random neighbour
     */
    public Room getRandomNeighbour()
    {
        List<Room> neighbours = new ArrayList<Room>(exits.values());
        int n = neighbours.size();
        if (n > 0) {
            Random rand = Randomizer.getRandom();
            return neighbours.get(rand.nextInt(n));
        }
        else {
            return null;
        }
    }
}

