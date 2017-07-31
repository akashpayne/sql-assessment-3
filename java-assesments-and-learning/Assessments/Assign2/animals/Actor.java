import java.util.List;
import java.util.Iterator;
/**
 * Interface Actor - two methods:
 * 
 * @author Akash Payne 
 * @version 19/02/2014
 */
public interface Actor
{
    /**
     * the hook for act functionality in actor's classes: Rabbit, Fox, etc.
     */
    void act(List<Actor> newActors);

    /**
     * Is the actor still active?
     * @return true if still 
     * active, false if not. 
     */ 
    boolean isActive(); 
}

