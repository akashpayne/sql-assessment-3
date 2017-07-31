import java.util.List;
import java.util.Random;

/**
 * Abstract class Organism - write a description of the class here
 * 
 * @author Akash Payne 
 * @version 19/02/2014
 */
public abstract class Organism implements Actor
{    
    // Characteristics shared by all organisms (static fields).
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();

    // The Organism's age.
    private int age;
    // Whether the organism is alive or not.
    private boolean alive;
    // The organism's field.
    private Field field;
    // The organism's position in the field.
    private Location location;
    // The type of Organism
    private String Organism_Type;
    
    /**
     * Create a new organism at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Organism(Field field, Location location)
    {
        age = 0;
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
      /**
     * Make this organism act - that is: make it do
     * whatever it wants/needs to do.
     * @param newOrganisms A list to add newly born organisms to.
     */
    abstract public void act(List<Actor> newAnimals);
    
    /**
     * Set the organism's age to the given value.
     * @param a New age.
     */
    public void setAge(int a) 
    {
        age = a;
    }
    
    /**
     * Increase the age. This could result in the organism's death.
     */
    public void incrementAge()
    {
        age++;
        if(age > getLifeTime()) {
            setDead();
        }
    }
    
    /**
     * An organism can breed if it has reached the breeding age.
     * @return Whether the organism can breed.
     */
    public boolean canBreed()
    {
        return age >= getSplittingAge();
    }  
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getSplittingProbability()) {
            births = rand.nextInt(getMaxSplitSize()) + 1;
        }
        return births;
    }
       
    /**
     * Check whether the organism is alive or not.
     * @return true if the organism is still alive.
     */
    public boolean isActive()
    {
        return alive;
    }

    /**
     * Indicate that the organism is no longer alive.
     * It is removed from the field.
     */
    public void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }
    
    /**
     * Return the organism's location.
     * @return The organism's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the organism's field.
     * @return The organism's field.
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * Place the organism at the new location in the given field.
     * @param newLocation The organism's new location.
     */
    public void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }
    
    /**
     * Return the breeding age of this organism.
     * @return The breeding age of this organism.
     */
    
    abstract protected int getSplittingAge();
    
    /**
     * Return the breeding probability of this organism.
     * @return The breeding probability of this organism.
     */
    
    abstract protected double getSplittingProbability();
    
    /**
     * Return the maximal litter size of this organism.
     * @return The maximal litter size of this organism.
     */
    
    abstract protected int getMaxSplitSize();
    
    /**
     * Return the maximal age of this organism.
     * @return The maximal age of this organism.
     */
    abstract protected int getLifeTime();
    
    abstract protected String getOrganismType();
    
    /**
     * Check whether or not this organismox is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newOrganisms A list to add newly born organisms to.
     */
    public void giveBirth(List<Actor> newAnimals)
    {
        // New foxes are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        Organism_Type = getOrganismType();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);            
            if (Organism_Type == "Rabbit"){
                Actor young = new Rabbit(false, field, loc);
                newAnimals.add(young);}
            if (Organism_Type == "Fox"){
                Actor young = new Fox(false, field, loc);
                newAnimals.add(young);} 
        }
    }
}