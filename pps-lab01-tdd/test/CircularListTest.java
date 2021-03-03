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

    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
    }

    private void addElementsToCircularList(final int...integers) {
        for (final int element : integers) {
            circularList.add(element);
        }
    }

    private List<Optional<Integer>> getAllNextOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            optionalValue.add(circularList.next());
        }
        return optionalValue;
    }

    private List<Optional<Integer>> getAllPreviousOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        for (int i = 0; i < numberElements; i++) {
            optionalValue.add(circularList.previous());
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
        assertEquals(circularList.size(), 3);
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
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 2, 3));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularNext() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 3));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testEmptyNext() {
        assertTrue(circularList.next().isEmpty());
    }

    @Test
    public void testSimplePrevious() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 3, 2));
        final List<Optional<Integer>> optionalListValue = this.getAllPreviousOptionalList(exceptedList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularPrevious() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 3, 2, 1, 3, 2));
        final List<Optional<Integer>> optionalListValue = this.getAllPreviousOptionalList(exceptedList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testEmptyPrevious() {
        assertTrue(circularList.previous().isEmpty());
    }

    @Test
    public void testPreviousNextCircularTest() {
        addElementsToCircularList(1, 2, 3);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 2, 1, 3, 1));

    }

}
