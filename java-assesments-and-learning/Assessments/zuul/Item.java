
/**
 * An object of this class is an item in the game of Zuul.
 * An item has a one word description and a strength.
 * 
 * @author Olaf Chitil
 * @version 17/3/2014
 */
public class Item
{
    private String description;
    private int strength;

    /**
     * Constructor for objects of class Item
     */
    public Item(String d, int s)
    {
        description = d;
        assert (s >= 1 && s <= 100);
        strength = s;
    }

    /**
     * Produce printable description of an item.
     * @return     the description
     */
    @Override
    public String toString()
    {
        return description;
    }
    
    /**
     * Get strength of the item.
     */
    public int getStrength()
    {
        return strength;
    }
}
