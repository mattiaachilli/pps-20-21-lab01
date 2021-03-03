import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    private void addElementsToCircularList(final int...integers) {
        for (final int element : integers) {
            circularList.add(element);
        }
    }

    private List<Optional<Integer>> getNextOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            optionalValue.add(circularList.next());
        }
        return optionalValue;
    }

    private List<Optional<Integer>> getPreviousOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        optionalValue.add(0, circularList.previous());
        for (int i = numberElements - 1; i >= 1; i++) {
            optionalValue.add(i, circularList.previous());
        }
        return optionalValue;
    }

    @Test
    public void testSimpleAdd() {
        circularList.add(1);
        assertEquals(circularList.size(), 1);
    }

    @Test
    public void testMultipleAddAndSize() {
        addElementsToCircularList(1, 2, 3);
        assertEquals(circularList.size(), NUMBER_OF_ELEMENT_TO_ADD);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        addElementsToCircularList(1, 2, 3);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> listExcepted = new ArrayList<>(Arrays.asList(1, 2, 3));
        final List<Optional<Integer>> optionalListValue = this.getNextOptionalList(NUMBER_OF_ELEMENT_TO_ADD);

        for (int i = 0; i < NUMBER_OF_ELEMENT_TO_ADD; i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(listExcepted.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularNext() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> listExcepted = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3));
        final List<Optional<Integer>> optionalListValue = this.getNextOptionalList(NUMBER_OF_ELEMENT_TO_ADD * 2);

        for (int i = 0; i < NUMBER_OF_ELEMENT_TO_ADD * 2; i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(listExcepted.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testEmptyNext() {
        assertTrue(circularList.next().isEmpty());
    }

    @Test
    public void testSimplePrevious() {
        addElementsToCircularList(NUMBER_OF_ELEMENT_TO_ADD);
        final List<Optional<Integer>> optionalListValue = this.getPreviousOptionalList(NUMBER_OF_ELEMENT_TO_ADD);

        assertFalse(optionalListValue.get(0).isEmpty());
        assertEquals(1, optionalListValue.get(0).get());
        for (int i = NUMBER_OF_ELEMENT_TO_ADD - 1; i >= 1; i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(i + 1, optionalListValue.get(i).get());
        }
    }

}
