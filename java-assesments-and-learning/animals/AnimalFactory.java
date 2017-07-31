import java.awt.Color;

/**
 * A class responsible for creating the initial population
 * of animals in the simulation.
 * 
 * @author David J. Barnes and Michael Kolling 
 * @version 2009.02.18
 */
public class AnimalFactory
{
    // The view displaying the status of the simulation.
    private SimulatorView view;
    
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
    public Animal optionallyCreateAnimal()
    {
        return null;
    }
    
    /**
     * Associate colors with the animal classes.
     */
    private void setupColors()
    {
        view.setColor(Rabbit.class, Color.orange);
        view.setColor(Fox.class, Color.blue);
    }
}
