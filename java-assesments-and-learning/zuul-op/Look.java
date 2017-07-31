
/**
 * the Look command of the game
 * It looks around the room and tells what available exits are in the currentroom
 * 
 * @author William Higgins 
 * @version 12/02/2015
 */
public class Look extends Command
{
    // instance variables - replace the example below with your own
   public String process(GameMain ui, Game game)
   {
       assert ui != null : "Help.process gets null ui";
       assert game != null : "Help.process gets null game";
       return game.look();
    
    }
}
