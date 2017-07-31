
/**
 * This is the take command
 * it takes the item or items in the currentroom you are in
 * you have to call the take command while playing the game
 * 
 * @author William Higgins 
 * @version 12/02/2015
 */
public class Take extends Command
{
    private Item item;
    // instance variables - replace the example below with your own
    public Take(Item item)
    {
        assert item != null : "Go.Go gets null direction";
        this.item = item;
    }
    
    public String process(GameMain ui, Game game)
    {
        assert game != null : "Go.process gets null game";
        return game.take(item); 
    }
}
