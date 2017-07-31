
/**
 * This is the drop command
 * it drops the item or items which you currently have with you
 * you have to specify which items you would like to drop
 * you have to call the drop command while playing the game
 * for example -- drop brick
 * 
 * @author William Higgins 
 * @version 12/02/2015
 */
public class Drop extends Command
{
    private Item item;
    // instance variables - replace the example below with your own
    public Drop(Item item)
    {
        assert item != null : "Go.Go gets null direction";
        this.item = item;
    }
    
    public String process(GameMain ui, Game game)
    {
        assert game != null : "Go.process gets null game";
        return game.drop(item); 
    }
}
