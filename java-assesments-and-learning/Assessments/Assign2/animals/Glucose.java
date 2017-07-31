import java.util.List;
import java.util.Iterator;

/**
 * Write a description of class Glucose here.
 * 
 * @author Akash Payne 
 * @version 19/02/2014
 */
public class Glucose extends Organism
{
    // Characteristics shared by all Atom1s (static fields).
    private static final String ORGANISM_TYPE = "Glucose";
    // The age at which a Glucose can start to breed.
    private static final int SPLITTING_AGE = 1;
    // The age to which a Glucose can live.
    private static final int LIFE_TIME = 300;
    // The likelihood of a Glucose breeding.
    private static final double SPLIT_PROBABILITY = 0.97;
    // The maximum number of Glucose births.
    private static final int MAX_SPLIT_SIZE = 7;
    // The Glucose's life value.
    private static final int GLUCOSE_LIFE_VALUE = 15;
    // Individual characteristics (instance fields).
    // The Glucose's food level
    private int foodLevel;
    
    /**
     * Create a Glucose. A Glucose can be created by splitting (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the Glucose will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Glucose(boolean randomAge, Field field, Location location)
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
     * This is what the Glucose does most of the time: it spreads for
     * Glucose. In the process, it might split, die of hunger,
     * or die of old "age".
     * @param field The field currently occupied.
     * @param newFoxes A list to add newly born Glucose to.
     */
    public void act(List<Actor> newAnimals)
    {
        incrementAge();
        if(isActive()) {
            giveBirth(newAnimals);            
            // Move towards a source of food if found.
            Location newLocation = 
                getField().freeAdjacentLocation(getLocation());
            if(newLocation == null) { 
                setLocation(newLocation);
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
     * Return the maximal age of the Glucose.
     * @return The maximal age of the Glucose.
     */
    protected int getLifeTime()
    {
        return LIFE_TIME;
    }
    
    /**
     * Return the splitting age of the Glucose.
     * @return The splitting age of the Glucose.
     */
    protected int getSplittingAge()
    {
        return SPLITTING_AGE;
    }

    /**
     * Return the breeding probability of the glucose.
     * @return The breeding probability of the glucose.
     */
    
    protected double getSplittingProbability()
    {
        return  SPLIT_PROBABILITY;
    }
    
    /**
     * Return the maximal Split size of the glucose.
     * @return The maximal Split size of the glucose.
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
