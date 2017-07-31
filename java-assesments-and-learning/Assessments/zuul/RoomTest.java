import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RoomTest.
 *
 * @author  Olaf Chitil
 * @version 28/2/2014
 */
public class RoomTest
{
    Room hall, kitchen;
    
    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        hall = new Room ("in the hall");
        kitchen = new Room ("in the kitchen");
        hall.setExit("north", kitchen);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    /**
     * Test that getting the exit of a room does indeed obtain the room that was set
     * as exit before.
     */
    @Test
    public void exit1()
    {
        assertEquals(kitchen, hall.getExit("north"));
    }
    
    /**
     * Test that getting the exit of a room where no such exit exists returns null.
     */
    @Test
    public void exit2()
    {
        assertNull(hall.getExit("south"));
    }

    /**
     * Test that a new room has no items. (Actually also tests that it has no exits.)
     */
    @Test
    public void empty()
    {
        assertEquals("You are in the kitchen.\nExits:", kitchen.getLongDescription());
    }
}

