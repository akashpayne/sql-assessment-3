import java.util.Random;
import java.awt.Color;

/**
 * A class responsible for creating the initial population
 * of animals in the simulation.
 * 
 * @author David J. Barnes, Michael Kolling and Olaf Chitil
 * @version 25/2/2014
 */
public class AnimalFactory implements Factory
{
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;    

    // Random number generator.
    private Random rand;
    
    /**
     * Constructor for objects of class AnimalFactory
     * @param view The visualisation of the simulation.
     */
    public AnimalFactory()
    {
        rand = Randomizer.getRandom();
    }
    
    /**
     * Optionally create an animal.
     * Whether an animal is created will depend upon probabilities
     * of animal creation.
     * @return A newly created Animal, or null if none is created.
     */
    public Actor optionallyCreateAnimal(Field field, int row, int col)
    {
        if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
            Location location = new Location(row, col);
            return new Fox(true, field, location);
        } else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
            Location location = new Location(row, col);
            return new Rabbit(true, field, location);
        } else {
            return null;
        }
    }
    
    /**
     * Associate colours with the animal classes.
     */
    public void setupColours(SimulatorView view)
    {
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
    }
}
