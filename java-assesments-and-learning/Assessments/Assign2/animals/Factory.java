import java.util.List;

/**
 * Factory Interface:
 * Construction of the factory.
 * 
 * @author Akash Payne 
 * @version 19/02/2014
 */
public interface Factory
{
    public Actor optionallyCreateActors(Field field, List<Actor> newActors);
    public void setupColors();
}
