
/**
 * This is the Go command of the game.
 * It requires a direction.
 * 
 * @author Olaf Chitil 
 * @version 28/1/2015
 */
public class Go extends Command
{
    private Direction direction;
    
    /**
     * Initialise the direction.
     * Pre-condition: direction is not null.
     */
    public Go(Direction direction)
    {
        assert direction != null : "Go.Go gets null direction";
        this.direction = direction;
    }
    
    /**
     * Go in the given direction.
     * Pre-condition: game is not null.
     */
    public String process(GameMain ui, Game game)
    {
        assert game != null : "Go.process gets null game";
        return game.goRoom(direction);
    }
}
