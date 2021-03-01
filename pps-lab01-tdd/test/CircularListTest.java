import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;

    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
    }

    private void addElementsCircularList(int... elements) {
        for (int value : elements) {
            circularList.add(value);
        }
    }

    @Test
    public void testAdd() {
        addElementsCircularList(0, 1);
        assertEquals(circularList.size(), 2);
    }

    @Test
    public void testSize() {
        addElementsCircularList(0, 1, 2);
        assertEquals(circularList.size(), 3);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        addElementsCircularList(0, 1, 2);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        addElementsCircularList(0, 1, 2);
        for (int i = 0; i < 3; i++) {
            optionalValue.add(circularList.next());
        }

        for (int i = 0; i < 3; i++) {
            assertFalse(optionalValue.get(i).isEmpty());
            assertEquals(i, optionalValue.get(i).get());
        }
    }

}
