import java.util.List;
import java.util.Iterator;

/**
 * Write a description of class Bacteria here.
 * 
 * @author Akash Payne 
 * @version 19/02/2014
 */
public class Bacteria extends Organism
{
    // Characteristics shared by all Atom1s (static fields).
    private static final String ORGANISM_TYPE = "Bacteria";
    // The age at which a Atom1 can start to breed.
    private static final int SPLITTING_AGE = 10;
    // The age to which a Atom1 can live.
    private static final int LIFE_TIME = 150;
    // The likelihood of a Atom1 breeding.
    private static final double SPLIT_PROBABILITY = 0.65;
    // The maximum number of births.
    private static final int MAX_SPLIT_SIZE = 5;
    // The food value of a single glucose. In effect, this is the
    // number of steps bacteria can go before it has to eat again.
    private static final int GLUCOSE_LIFE_VALUE = 7;
    // Individual characteristics (instance fields).
    // The bacteria's food level, which is increased by eating glucose.
    private int foodLevel;
    
    /**
     * Create a Bacteria. A Bacteria can be created by splitting (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Bacteria will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Bacteria(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            setAge(rand.nextInt(LIFE_TIME));
            foodLevel = rand.nextInt(GLUCOSE_LIFE_VALUE);
        }
        else {
            foodLevel = GLUCOSE_LIFE_VALUE;
        }
    }
    
    /**
     * This is what the Bacteria does most of the time: it spreads for
     * Glucose. In the process, it might split, die of hunger,
     * or die of old "age".
     * @param field The field currently occupied.
     * @param newFoxes A list to add newly born Bacteria to.
     */
    public void act(List<Actor> newAnimals)
    {
        incrementAge();
        incrementHunger();
        if(isActive()) {
            giveBirth(newAnimals);            
            // Move towards a source of food if found.
            Location location = getLocation();
            Location newLocation = findFood(location);
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(location);
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Make this Bacteria more hungry. This could result in the bacteria's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Tell the Bacteria to look for Glucose near to its current location.
     * Only the first live Glucose is eaten.
     * @param location Where in the field it is located.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood(Location location)
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object organism = field.getObjectAt(where);
            if(organism instanceof Glucose) {
                Glucose glucose = (Glucose) organism;
                if(glucose.isActive()) { 
                    glucose.setDead();
                    foodLevel = GLUCOSE_LIFE_VALUE;
                    // Remove the dead Glucose from the field.
                    return where;
                }
            }
        }
        return null;
    }
        
    /**
     * Return the maximal age of the Bacteria.
     * @return The maximal age of the Bacteria.
     */
    protected int getLifeTime()
    {
        return LIFE_TIME;
    }
    
    /**
     * Return the splitting age of the Bacteria.
     * @return The splitting age of the Bacteria.
     */
    protected int getSplittingAge()
    {
        return SPLITTING_AGE;
    }

    /**
     * Return the breeding probability of the bacteria.
     * @return The breeding probability of the bacteria.
     */
    
    protected double getSplittingProbability()
    {
        return  SPLIT_PROBABILITY;
    }
    
    /**
     * Return the maximal Split size of the bacteria.
     * @return The maximal Split size of the bacteria.
     */
    
    protected int getMaxSplitSize()
    {
        return MAX_SPLIT_SIZE;
    }
    
    protected String getOrganismType()
    {
        return ORGANISM_TYPE;
    }
}
