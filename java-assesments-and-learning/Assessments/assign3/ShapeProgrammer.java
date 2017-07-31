
/**
 * A class that allows the user to program with shapes.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ShapeProgrammer
{
    // Obtain commands from the user.
    private InputReader reader;

    /**
     * Constructor for objects of class ShapeProgrammer
     */
    public ShapeProgrammer()
    {
        reader = new InputReader();
    }
    
    /**
     * Allow the user to program with shapes interactively.
     * Repeatedly request commands until the user types "quit".
     */
    public void program()
    {
        printWelcome();
        // Keep reading commands until the user types quit.
        boolean programming = true;
        
        while(programming) {
            String[] commands = reader.getInput();
            if(commands.length > 0) {
                // Handle program exit here.
                if(commands[0].equals("quit")) {
                   programming = false;
                }
                else {
                    execute(commands);
                }
            }
        }
        
        printGoodbye();
    }
    
    /**
     * Print a welcome message.
     */
    private void printWelcome()
    {
        System.out.println("Welcome to the shape programmer.");
        printHelp();
    }
    
    /**
     * Print a goodbye message.
     */
    private void printGoodbye()
    {
        System.out.println("Goodbye");
    }
    
    /**
     * Print the help.
     */
    private void printHelp()
    {
        System.out.println("Type commands like: ");
        System.out.println("    circle");
        System.out.println("to create a circle.");
        System.out.println("Type: ");
        System.out.println("    quit");
        System.out.println("to finish.");
    }
    
    /**
     * Execute the command recorded in commands.
     * @commands The individual words of the command.
     *           commands.length > 0
     */
    private void execute(String[] commands)
    {
        String basicCommand = commands[0];
        if(basicCommand.equals("circle")) {
            makeACircle(commands);
        }
        else if(basicCommand.equals("help")) {
            printHelp();
        }
        else {
            System.out.println("Unknown command: " + basicCommand);
        }
    }
    
    /**
     * Create a Circle and make it visible.
     * @param commands The current command words.
     *                 Not currently used.
     */
    private void makeACircle(String[] commands)
    {
        Circle c = new Circle();
        c.makeVisible();
    }

}
