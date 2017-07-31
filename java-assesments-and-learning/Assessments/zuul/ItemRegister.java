import java.util.Map;
import java.util.HashMap;

/**
 * An ItemRegister keeps track of all items appearing in a game.
 * It allows translating an item description into the item itself.
 * 
 * @author Olaf Chitil
 * @version 28/2/2014
 */
public class ItemRegister
{
    Map<String,Item> register;
    
    /**
     * Constructor for objects of class ItemRegister
     */
    public ItemRegister()
    {
        register = new HashMap<String,Item>();
    }

    /**
     * Register an item.
     * Assumes that no item with the same description already exists in the register.
     * 
     * @param   item    The item to register.
     */
    public void register(Item item)
    {
        assert !register.containsKey(item.toString());  
        // checks assumption; not required but useful for locating potential bugs
        register.put(item.toString(), item);
    }
    
    /**
     * Lookup an item by its description in the register.
     * Returns null if no such entry in the register.
     * 
     * @param  description  The description to be looked up.
     * @return  the corresponding item in the register
     */
    public Item fromString(String description)
    {
        return register.get(description);
    }
}
