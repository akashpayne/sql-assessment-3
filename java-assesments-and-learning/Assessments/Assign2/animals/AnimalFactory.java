import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * A class responsible for creating the initial population
 * of animals in the simulation.
 * 
 * @author David J. Barnes and Michael Kolling 
 * @version 2009.02.18
 * @edited Akash Payne
 * @date 19/02/2014
 */
public class AnimalFactory implements Factory
{
    // The view displaying the status of the simulation.
    private SimulatorView view;
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08; 

    // The factory production
    private Factory factory;
    /**
     * Constructor for objects of class AnimalFactory
     * @param view The visualisation of the simulation.
     */
    public AnimalFactory(SimulatorView view) 
    {
        this.view = view;
        setupColors();
    }
    
    /**
     * Optionally create an animal.
     * Whether an animal is created will depend upon probabilities
     * of animal creation.
     * @return A newly created Animal, or null if none is created.
     */
    public Actor optionallyCreateActors(Field field, List<Actor> newActors)
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Fox fox = new Fox(true, field, location);
                    newActors.add(fox);
                }
                else if(rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    newActors.add(rabbit);
                }
                // else leave the location empty.
            }
        }
        return null;
    }
    
    /**
     * Associate colors with the animal classes.
     */
    public void setupColors()
    {
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
    }
}
