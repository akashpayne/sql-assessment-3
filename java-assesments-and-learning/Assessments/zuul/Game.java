/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 28/2/2014
 */

public class Game 
{
    private static final int TIME_LIMIT = 25;
    
    private Parser parser;
    private Player player;
    private Room goal;
    private int time;
    private ItemRegister allItems;
    private CharacterRegister allCharacters;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        allItems = new ItemRegister();
        allCharacters = new CharacterRegister();
        player = new Player(createRooms());
        parser = new Parser();
        time = TIME_LIMIT;
    }

    /**
     * Create all the rooms and link their exits together.
     * @return The room in which the game starts.
     */
    private Room createRooms()
    {
        Room gate, graveyard, church, crypt, entrance, hall, kitchen, buttery, greathall, staircase,
            dungeon, topstaircase, throne, solar, wardrobe, privy;
            
        Item key, sword, candle;
        
        Character dragon, sorcerer, dwarf;
        
        // create the items
        key = new Item("key", 50);
        sword = new Item("sword", 70);
        candle = new Item("candle", 40);
        
        // register the items
        allItems.register(key);
        allItems.register(sword);
        allItems.register(candle);
        
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
        
        // add items to rooms
        church.add(key);
        hall.add(candle);
        greathall.add(sword);
        
        // create the characters
        dragon = new Character("dragon", staircase, 50);
        sorcerer = new Character("sorcerer", throne, 30);
        dwarf = new Character("dwarf", church, 10);
        
        // register the characters
        allCharacters.register(dragon);
        allCharacters.register(sorcerer);
        allCharacters.register(dwarf);
        
        // initialise room exits
        
        gate.setExit("north", graveyard);
        
        graveyard.setExit("south", gate);
        graveyard.setExit("east", church);
        graveyard.setExit("north", entrance);
        
        church.setExit("west", graveyard);
        church.setExit("south", crypt);
        
        crypt.setExit("north", church);
        
        entrance.setExit("south", graveyard);
        entrance.setExit("north", hall);
        
        hall.setExit("south", graveyard);
        hall.setExit("west", kitchen);
        hall.setExit("north", greathall);
        hall.setExit("east", staircase);
        
        kitchen.setExit("east", hall);
        kitchen.setExit("south", buttery);
        
        buttery.setExit("north", kitchen);
        
        greathall.setExit("south", hall);
        
        staircase.setExit("west", hall);
        staircase.setExit("down", dungeon);
        staircase.setExit("up", topstaircase);
        
        topstaircase.setExit("down", staircase);
        topstaircase.setExit("north", throne);
        topstaircase.setExit("south", solar);
        
        throne.setExit("south", topstaircase);
        
        solar.setExit("north", topstaircase);
        solar.setExit("west", wardrobe);
        solar.setExit("east", privy);
        
        wardrobe.setExit("east", solar);
        
        privy.setExit("west", solar);
        
        goal = throne; // set the room that finishes the game
        
        return gate;  // start game at gate
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            if (time <= 0) {
                System.out.println("You ran out of time. You lost.");
                finished = true;
            } else {
                Command command = parser.getCommand();
                finished = processCommand(command);
            }
            time--;
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, mysterious adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                allCharacters.maybeMove();  // easier to play if only here
                wantToQuit = goRoom(command);
                break;
                
            case TAKE:
                take(command);
                break;
                
            case DROP:
                drop(command);
                break;
                
            case FIGHT:
                wantToQuit = fight(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander around an ancient castle.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @return true, if this command quits the game, false otherwise
     */
    private boolean goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return false;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            player.setCurrentRoom(nextRoom);
            System.out.println(player.getCurrentRoom().getLongDescription());
        }
        
        if(player.getCurrentRoom() == goal) {
            System.out.println("Congratulations! You have reached the goal of the game and won.");
            return true;
        }
        
        return false;
    }
    
    /** 
     * "Take item" was entered. Try to take the item from the room.
     * If it is not an item in the game or in the room, then produce appropriate error message.
     */
    private void take(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        
        String description = command.getSecondWord();
        Item item = allItems.fromString(description);
        
        if (item == null) {
            System.out.println(description + " is not an item in this game.");
            return;
        }
        
        boolean successful = player.take(item);
        if (successful) {
            System.out.println("You are now holding the " + description + ".");
        } else {
            System.out.println("This room does not hold the " + description + ".");
        }
    }
    
    /** 
     * "Drop item" was entered. Try to drop the item in the current room.
     * If it is not an item in the game or not held by the player, then produce appropriate message.
     */
    private void drop(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Drop what?");
            return;
        }
        
        String description = command.getSecondWord();
        Item item = allItems.fromString(description);
        
        if (item == null) {
            System.out.println(description + " is not an item in this game.");
            return;
        }
        
        boolean successful = player.drop(item);
        if (successful) {
            System.out.println("You have dropped the " + description + ".");
        } else {
            System.out.println("You do not hold the " + description + ".");
        }
    }

    /**
     * "Fight character" was entered. Fight the character which will lead to the character's or
     * player's death.
     * Even if "character" is not a valid input, always produce an appropriate message.
     */
    private boolean fight(Command command)
    {
        if (!command.hasSecondWord()) {
            System.out.println("Fight whom?");
            return false;
        }
        
        String description = command.getSecondWord();
        Character character = allCharacters.fromString(description);
        
        if (character == null) {
            System.out.println(description + " is not a character in this game.");
            return false;
        }
        
        if (!character.isIn(player.getCurrentRoom())) {
            System.out.println(description + " is not with you " + 
                player.getCurrentRoom().getShortDescription());
            return false;
        }
        
        boolean successful = character.fight(player);
        if (successful) {
            System.out.println("The " + description + " won. You have lost.");
            return true;
        } else {
            System.out.println("You have won. The " + description + " has lost.");
            allCharacters.remove(character);
            return false;
        }
        
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
