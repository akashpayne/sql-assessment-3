import java.util.Scanner;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two-word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author  Michael Kölling, David J. Barnes and Olaf Chitil
 * @version 28/1/2015
 * 
 * @editor william Higgins
 * @12/02/2015
 * 
 * The changes include that you can use the enum direction
 * This converts it from a string and returns a command object.
 * extend the string commands
 * so that it shows take, help and look.
 * 
 */
public class Parser 
{
    private Scanner reader;         // source of command input
    private Direction direction;
    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() 
    {
        reader = new Scanner(System.in);
    }

    /**
     * @return The next command from the user.
     */
    public Command getCommand() 
    {
        String inputLine;   // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("\n> ");     // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            switch (word1) {
                case "go" : if(tokenizer.hasNext()) {
                    /** 
                    } */  
                    word2 = tokenizer.next().toLowerCase();                                
                    // get second word
                    switch(word2) {
                        case "north" : return new Go(Direction.NORTH); 
                        case "south" : return new Go(Direction.SOUTH);
                        case "east" : return new Go(Direction.EAST);
                        case "west" : return new Go(Direction.WEST);
                        case "up" : return new Go(Direction.UP);
                        case "down" : return new Go(Direction.DOWN);
                    }
                }
                break;
                case "help" : return new Help();
                case "quit" : return new Quit();
                case "look" : return new Look();
                case "take" : if (tokenizer.hasNext()) {
                    word2 = tokenizer.next().toLowerCase();
                    switch(word2){
                        case "brick" : return new Take(Item.BRICK);
                        case "knife" : return new Take(Item.KNIFE);
                        case "hammer" : return new Take(Item.HAMMER);
                    }
                }
                case "drop" : if (tokenizer.hasNext()) {
                    word2 = tokenizer.next().toLowerCase();
                    switch(word2){
                        case "brick" : return new Drop(Item.BRICK);
                        case "knife" : return new Drop(Item.KNIFE);
                        case "hammer" : return new Drop(Item.HAMMER);
                    }
                }
            }
            // note: we just ignore the rest of the input line.
        }
        return new Command(); // unknown command
    }

    /**
     * Returns a list of valid commands.
     */
    public String commands()
    {
        return "go <direction>, quit, help, look, take<item>, drop<item>";
    }

    /**
     * Returns name of help command.
     */
    public String help()
    {
        return "help";
    }

    public String look()
    {
        return "look";
    }

    public Direction direction()
    {
        return direction;
    }
}
