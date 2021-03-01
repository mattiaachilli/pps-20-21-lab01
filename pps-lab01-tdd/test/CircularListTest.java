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
    private final static int NUMBER_OF_ELEMENT_TO_ADD = 3;

    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
    }

    private void addElementsToCircularList(final int nElementToAdd) {
        for (int i = 0; i < nElementToAdd; i++) {
            circularList.add(i);
        }
    }

    private List<Optional<Integer>> getOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            optionalValue.add(circularList.next());
        }
        return optionalValue;
    }

    @Test
    public void testAddAndSize() {
        addElementsToCircularList(NUMBER_OF_ELEMENT_TO_ADD);
        assertEquals(circularList.size(), NUMBER_OF_ELEMENT_TO_ADD);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        addElementsToCircularList(NUMBER_OF_ELEMENT_TO_ADD);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        addElementsToCircularList(NUMBER_OF_ELEMENT_TO_ADD);
        final List<Optional<Integer>> optionalListValue = this.getOptionalList(NUMBER_OF_ELEMENT_TO_ADD);

        for (int i = 0; i < NUMBER_OF_ELEMENT_TO_ADD; i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(i, optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularNext() {
        addElementsToCircularList(NUMBER_OF_ELEMENT_TO_ADD);
        final List<Optional<Integer>> optionalListValue = this.getOptionalList(NUMBER_OF_ELEMENT_TO_ADD);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < NUMBER_OF_ELEMENT_TO_ADD; j++) {
                assertFalse(optionalListValue.get(j).isEmpty());
                assertEquals(j, optionalListValue.get(j).get());
            }
        }
    }

}
