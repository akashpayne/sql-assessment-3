
/**
 * Type of any valid command of the game.
 * An object of this class is the Unkown command, a command the game does not understand.
 * All commands are subclasses of this class.
 * 
 * @author Olaf Chitil
 * @version 28/1/2015
 */
public class Command
{
    /**
     * Process, that is, execute the command in the game.
     * @param game The actual game.
     * @return Message to the player.
     */
    public String process(GameMain ui, Game game) 
    {
        return "I don't know what you mean...";
    }
}
