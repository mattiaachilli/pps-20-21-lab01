import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;

    @BeforeAll
    public void init() {
        this.circularList = new CircularListImpl();
    }

    @Test
    public void testAdd() {
        circularList.add(0);
        Assertions.assertEquals(circularList.size(), 1);
    }

    @Disabled
    @Test public void testTodo(){
        Assertions.fail();
    }

}
