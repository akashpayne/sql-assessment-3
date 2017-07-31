import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of animals.
 * 
 * @author David J. Barnes, Michael Kolling and Olaf Chitil
 * @version 25/2/2014
 */
public abstract class Animal implements Actor
{
    // Characteristics shared by all animals (static fields).
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();

    // The animal's age.
    private int age;
    // Whether the animal is alive or not.
    private boolean alive;
    // The animal's field.
    private Field field;
    // The animal's position in the field.
    private Location location;
    
    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Animal(Field field, Location location)
    {
        age = 0;
        alive = true;
        this.field = field;
        setLocation(location);
    }
    
    /**
     * Set the animal's age to the given value.
     * @param a New age.
     */
    public void setAge(int a) 
    {
        age = a;
    }

    /**
     * Increase the age. This could result in the animal's death.
     */
    public void incrementAge()
    {
        age++;
        if(age > getMaxAge()) {
            setDead();
        }
    }
   
    /**
     * Check whether or not this animal is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newAnimals A list to add newly born animals to.
     */
    protected void giveBirth(List<Actor> newAnimals)
    {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = newAnimal(loc);
            newAnimals.add(young);
        }
    }
    
    /**
     * Construct a new animal.
     */
    abstract protected Animal newAnimal(Location location);
    
    /**
     * An animal can breed if it has reached the breeding age.
     * @return Whether the animal can breed.
     */
    public boolean canBreed()
    {
        return age >= getBreedingAge();
    }  
    
    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        int births = 0;
        if(canBreed() && rand.nextDouble() <= getBreedingProbability()) {
            births = rand.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }
    
    /**
     * Check whether the animal is alive or not.
     * @return true if the animal is still alive.
     */
    public boolean isActive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
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
     * Return the animal's location.
     * @return The animal's location.
     */
    public Location getLocation()
    {
        return location;
    }
    
    /**
     * Return the animal's field.
     * @return The animal's field.
     */
    public Field getField()
    {
        return field;
    }
    
    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
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
     * Return the breeding probability of this animal.
     * @return The breeding probability of this animal.
     */
    
    abstract protected double getBreedingProbability();
    
    /**
     * Return the maximal litter size of this animal.
     * @return The maximal litter size of this animal.
     */
    
    abstract protected int getMaxLitterSize();

    /** 
     * Return the breeding age of this animal.
     * @return The breeding age of this animal.
     */
    abstract protected int getBreedingAge();
    
    /**
     * Return the maximal age of this animal.
     * @return The maximal age of this animal.
     */
    abstract protected int getMaxAge();
    
    
}
