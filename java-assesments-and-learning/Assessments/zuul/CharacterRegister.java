import java.util.Map;
import java.util.HashMap;

/**
 * A CharacterRegister keeps track of all characters appearing in a game.
 * It allows translating a character description into the character itself.
 * 
 * @author Olaf Chitil
 * @version 16/3/2014
 */
public class CharacterRegister
{
   Map<String,Character> register;
   
   /**
    * Constructor for objects of class CharacterRegister
    */
   public CharacterRegister()
   {
       register = new HashMap<String,Character>();
   }
   
   /**
    * Register a character.
    * Assumes that no character with the same description already exists.
    * @param c  The character to register.
    */
   public void register(Character c)
   {
       assert !register.containsKey(c.toString());
       // checks assumption; not required but useful for locating a fault
       register.put(c.toString(), c);
    }
    
    /**
     * Lookup a character by its description in the register.
     * Returns null if no such entry in the register.
     * @param description   The description to be looked up.
     * @return  the corresponding character in the register
     */
    public Character fromString(String description)
    {
        return register.get(description);
    }
    
    /**
     * Possibly move all characters to some neighbouring rooms.
     */
    public void maybeMove()
    {
        for (Character c : register.values()) {
            c.maybeMove();
        }
    }
    
    /**
     * Remove given character from the register and also from its room.
     * Assumes the character is in the register and in a room.
     */
    public void remove(Character c)
    {
        register.remove(c);
        c.remove();
    }
}
