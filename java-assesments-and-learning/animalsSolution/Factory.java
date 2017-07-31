
/**
 * An interface responsible for creating the initial population of actors
 * in the simulation.
 * 
 * @author Olaf Chitil
 * @version 25/2/2014
 */

public interface Factory
{
    /**
     * Optionally create an actor.
     * Whether an actor is created will depend upon probabilities
     * of actor creation.
     * @return A newly created Actor, or null if none is created.
     */
    Actor optionallyCreateAnimal(Field field, int row, int col);
    
    /**
     * Associate colors with the animal classes.
     */
    void setupColours(SimulatorView view);
}
