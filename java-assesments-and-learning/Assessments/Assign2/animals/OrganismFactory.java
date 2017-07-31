import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * Write a description of class OrganismFactory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OrganismFactory implements Factory
{
     // The view displaying the status of the simulation.
    private SimulatorView view;
 
    // The probability that a bacteria will be created in any given grid position.
    private static final double BACTERIA_CREATION_PROBABILITY = 0.02;
    // The probability that a glucose will be created in any given grid position.
    private static final double GLOCUSE_CREATION_PROBABILITY = 0.08;    

    private Factory factory;
    /**
     * Constructor for objects of class OrganismFactory
     * @param view The visualisation of the simulation.
     */
    public OrganismFactory(SimulatorView view) 
    {
        this.view = view;
        setupColors();
    }
    
    /**
     * Optionally create an organism.
     * Whether an organism is created will depend upon probabilities
     * of organism creation.
     * @return A newly created Organism, or null if none is created.
     */
    public Actor optionallyCreateActors(Field field, List<Actor> newActors)
    {
        Random rand = Randomizer.getRandom();
        field.clear();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                if(rand.nextDouble() <= BACTERIA_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Bacteria bacteria = new Bacteria(true, field, location);
                    newActors.add(bacteria);
                }
                else if(rand.nextDouble() <= GLOCUSE_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Glucose glucose = new Glucose(true, field, location);
                    newActors.add(glucose);
                }
                // else leave the location empty.
            }
        }
        return null;
    }
    
    /**
     * Associate colors with the organism classes.
     */
    public void setupColors()
    {
        view.setColor(Glucose.class, Color.green);
        view.setColor(Bacteria.class, Color.red);
    }
}