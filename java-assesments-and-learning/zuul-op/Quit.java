
/**
 * The Quit command of the game.
 * It just ends the game.
 * 
 * @author Olaf Chitil 
 * @version 28/1/2015
 */
public class Quit extends Command
{
    /**
     * Just tells the game to quit.
     * Pre-condition: game must not be null.
     */
    public String process(GameMain ui, Game game)
    {
        assert game != null;
        return game.quit();
    }
}
