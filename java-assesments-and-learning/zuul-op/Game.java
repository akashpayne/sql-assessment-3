import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;

/**
 *  This class is the central class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 28/2/2015
 * 
 * @editor william Higgins
 * @12/02/2015
 */

public class Game 
{
    private Room currentRoom;
    private boolean finished;
    private Room throne;   
    static int count = 0;
    private Parser parser;
    private Direction direction;
    private int totalWeight;
    private HashMap<Item, Integer> inventory;
    private int itemWeight;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        finished = false;
        createRooms();
        totalWeight = 0;
        inventory = new HashMap<Item, Integer>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room gate, graveyard, church, crypt, entrance, hall, kitchen, buttery, greathall, staircase,
        dungeon, topstaircase, solar, wardrobe, privy;

        // create the rooms
        gate = new Room("outside the old gate of the castle");
        graveyard = new Room("on a wind-swept gaveyard");
        church = new Room("in a small ancient church with medieval windows");
        crypt = new Room("in the crypt of the church");
        entrance = new Room("at the big wooden entrance of the castle");
        hall = new Room("in the dark entrance hall of the castle");
        kitchen = new Room("in the kitchen with a huge table and a big stove");
        buttery = new Room("in the buttery of the castle");
        greathall = new Room("in the great hall of the castle with its magnificient huge windows");
        staircase = new Room("at the staircase");
        dungeon = new Room("in the dark dungeon of the castle");
        topstaircase = new Room("at the top of the staircase");
        throne = new Room("in the throne room with golden walls");
        solar = new Room("in the solar of the castle");
        wardrobe = new Room("in the wardroble of the Lord of the castle");
        privy = new Room("in the privy");

        // initialise room exits

        gate.setExit(Direction.NORTH, graveyard);
        gate.setItem(Item.BRICK, gate);

        graveyard.setExit(Direction.SOUTH, gate);
        graveyard.setExit(Direction.EAST, church);  
        graveyard.setExit(Direction.NORTH, entrance);
        graveyard.setItem(Item.BRICK, graveyard);

        church.setExit(Direction.WEST, graveyard);
        church.setExit(Direction.SOUTH, crypt);
        church.setItem(Item.BRICK, church);

        crypt.setExit(Direction.NORTH, church);
        crypt.setItem(Item.HAMMER, crypt);

        entrance.setExit(Direction.SOUTH, graveyard);
        entrance.setExit(Direction.NORTH, hall);
        entrance.setItem(Item.KNIFE, entrance);

        hall.setExit(Direction.SOUTH, graveyard);
        hall.setExit(Direction.WEST, kitchen);
        hall.setExit(Direction.NORTH, greathall);
        hall.setExit(Direction.EAST, staircase);

        kitchen.setExit(Direction.EAST, hall);
        kitchen.setExit(Direction.SOUTH, buttery);
        kitchen.setItem(Item.KNIFE, kitchen);

        buttery.setExit(Direction.NORTH, kitchen);
        buttery.setItem(Item.HAMMER, buttery);

        greathall.setExit(Direction.SOUTH, hall);

        staircase.setExit(Direction.WEST, hall);
        staircase.setExit(Direction.DOWN, dungeon);
        staircase.setExit(Direction.UP, topstaircase);
        staircase.setItem(Item.BRICK, staircase);
        staircase.setItem(Item.HAMMER, staircase);

        topstaircase.setExit(Direction.DOWN, staircase);
        topstaircase.setExit(Direction.NORTH, throne);
        topstaircase.setExit(Direction.SOUTH, solar);

        throne.setExit(Direction.SOUTH, topstaircase);
        throne.setItem(Item.HAMMER,throne);

        solar.setExit(Direction.NORTH, topstaircase);
        solar.setExit(Direction.WEST, wardrobe);
        solar.setExit(Direction.EAST, privy);
        solar.setItem(Item.KNIFE, solar);
        solar.setItem(Item.HAMMER, solar);

        wardrobe.setExit(Direction.EAST, solar);

        privy.setExit(Direction.WEST, solar);

        currentRoom = gate;  // start game at gate

    }

    /**
     * Return whether the game has finished or not.
     */
    public boolean finished()
    {
        return finished;
    }

    /**
     * Opening message for the player.
     */
    public String welcome()
    {
        return "\nWelcome to the World of Zuul!\n" +
        "World of Zuul is a new, mysterious adventure game.\n" +
        currentRoom.getLongDescription() + "\n";
    }

    /**
     * Give some help information.
     */
    public String help() 
    {
        return "You are lost. You are alone. You wander around an ancient castle.\n";
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param direction The direction in which to go.
     * Pre-condition: direction is not null.
     */
    public String goRoom(Direction direction) 

    //Command command
    {
        assert direction != null : "Game.goRoom gets null direction";     
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            return "There is no exit in that direction!";
        }
        if (nextRoom == throne){
            currentRoom = nextRoom;
            finished = true;
            return currentRoom.getShortDescription() +  " \n> you have won";
        }

        else {
            currentRoom = nextRoom;
            count++;
            System.out.println("number of turns = " + count);
            return currentRoom.getLongDescription();
        }
    }

    public String count ()
    {
        if (count == 12) {
            finished = true;
        }
        return" You ran out of time. ";
    }

    /**
     * Execute quit command.
     */
    public String quit()
    {
        finished = true;
        return "Thank you for playing.  Good bye.";
    }

    public String look()
    {
        return currentRoom.getLongDescription();
    }

    /**
     * Method for taking an item from the current room
     * @param item String description of the item
     */
    /**
    public boolean takeItem(String item)
    {
    Item itemWanted = register.fromString(item);

    ArrayList<Item> currentRoomItems = currentRoom.getItems();

    if(currentRoomItems.contains(itemWanted))
    {
    items.add(itemWanted);
    currentRoom.removeItem(itemWanted);
    return true;
    }
    return false;
    //return currentRoomItems;
    } 
     */

    public String take(Item item)
    {
        int itemWeight = item.getWeight();
        if ((itemWeight + totalWeight)<30 ) {
            if(currentRoom.getItems().containsKey(item)){
                totalWeight = itemWeight + totalWeight; 
                addInventory(item, itemWeight);
                String weightString = ("Weight is now : "+totalWeight);
                currentRoom.removeItem(item,currentRoom);
                String returnString = item + " has been taken." + ".\n" + weightString;
                return returnString;
            } else
            {
                String returnString = "Item is not in room." ;
                return returnString;
            }
        }
        else {
            String returnString = "Item is too heavy." ;
            return returnString;
        }
    }

    public String drop(Item item)
    {
        int itemWeight = item.getWeight();
        if(totalWeight == 0){
            return "no items to drop";
        } else {
            totalWeight = totalWeight - itemWeight;
            removeInventory(item, itemWeight);
            currentRoom.removeItem(item,currentRoom);
            String weightString = ("Weight is now : "+totalWeight);
            String returnString = item + "has been dropped.";
            return returnString + ".\n" + weightString;
        }
    }

    public void addInventory(Item item, int itemWeight)
    {
        inventory.put(item, itemWeight);
    }

    public void removeInventory(Item item, int itemWeight)
    {
        inventory.remove(item, itemWeight);
    }
}
