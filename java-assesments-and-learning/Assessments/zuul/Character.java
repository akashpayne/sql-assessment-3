import java.util.Random;

/**
 * A character has description and is in a Room.
 * It can move and can be fought
 * 
 * @author Olaf Chitil
 * @version 17/3/2014
 */
public class Character
{
    // probability that a character will move in a round
    private static final double CHARACTER_MOVE_PROBABILITY = 0.3;
    
    private String description;
    private int strength;
    private Room room;
    
    /**
     * Constructor for objects of class Character
     * @param d a single word describing the character
     * @param r the room in which the character is
     * @param s the strength of the character 1-100
     */
    public Character(String d, Room r, int s)
    {
        description = d;
        room = r;
        room.add(this);
        assert (s >= 1 && s <= 100);
        strength = s;
    }
    
    /**
     * Produce printable description of a character.
     * @return  the description
     */
    @Override
    public String toString()
    {
        return description;
    }
    
    /**
     * Possibly move in a random neighbouring room.
     */
    public void maybeMove()
    {
        Random rand = Randomizer.getRandom();
        if (rand.nextDouble() < CHARACTER_MOVE_PROBABILITY) {
            Room next = room.getRandomNeighbour();
            if (next != null) {
                room.remove(this);
                room = next;
                room.add(this);
            }
        }
    }
    
    /**
     * Fight the character with a given strength.
     * @param player  The opponent.
     * @return true if character won
     *         false if character lost
     */
    public boolean fight(Player player)
    {
        Random rand = Randomizer.getRandom();
        return rand.nextInt(strength+player.getStrength()) < strength;
    }
    
    /**
     * Check whether character is in the given room.
     * @return true, if it is in the room; false otherwise
     */
    public boolean isIn(Room room)
    {
        return this.room == room;
    }
    
    /**
     * Remove character from its room (thus from the game).
     */
    public void remove()
    {
        room.remove(this);
    }
}
