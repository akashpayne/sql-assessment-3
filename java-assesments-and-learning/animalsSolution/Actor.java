import java.util.List;

/**
 * Actors for the simulation. Just two methods
 * 
 * @author Olaf Chitil
 * @version 25/2/2014
 */

public interface Actor
{
    /**
     * Do a life step and possible produce new offspring.
     */
    void act(List<Actor> newActors);
    
    /**
     * Check whether still alive.
     */
    boolean isActive();
}
