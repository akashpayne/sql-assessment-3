
/**
 * This is the Item enum class
 * This holds all of the items of the game
 * It has the weight and the name of each item
 * 
 * 
 * @author William Higgins 
 * @version 12/02/2015
 */
public enum Item
{
    BRICK("brick", 12), KNIFE("knife", 5), HAMMER("hammer", 8);
    
    private String itemString;
    private int itemWeight;
    
    Item(String itemString, int itemWeight)
    {
        this.itemString = itemString;    
        this.itemWeight = itemWeight;
    }
    
    public String toString()
    {
        return itemString;
    }
    
    
    public int getWeight()
    {
        return itemWeight;
   }
}

