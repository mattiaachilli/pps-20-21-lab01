import lab01.tdd.*;
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
    protected final static List<Integer> LIST_OF_NUMBER = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
    }

    protected final CircularList getCircularList() {
        return this.circularList;
    }

    protected final void addElementsToCircularList(final List<Integer> integerList) {
        for (final int element : integerList) {
            circularList.add(element);
        }
    }

    private List<Optional<Integer>> getAllNextOptionalList(final int numberElements, final SelectStrategy strategy) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        if (numberElements > 0) {
            if(strategy != null) {
                for (int i = 0; i < numberElements; i++) {
                    optionalValue.add(circularList.next(strategy));
                }
            } else {
                for (int i = 0; i < numberElements; i++) {
                    optionalValue.add(circularList.next());
                }
            }
        }
        return optionalValue;
    }

    private List<Optional<Integer>> getAllPreviousOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        if (numberElements > 0) {
            for (int i = 0; i < numberElements; i++) {
                optionalValue.add(circularList.previous());
            }
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
        addElementsToCircularList(LIST_OF_NUMBER);
        assertEquals(circularList.size(), LIST_OF_NUMBER.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        addElementsToCircularList(LIST_OF_NUMBER);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = LIST_OF_NUMBER;
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), null);

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularNext() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3);
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), null);

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
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = Arrays.asList(1, 10, 9, 8, 7, 6, 5);
        final List<Optional<Integer>> optionalListValue = this.getAllPreviousOptionalList(exceptedList.size());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testCircularPrevious() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = Arrays.asList(1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10);
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
    public void testPreviousNextCircular() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 2, 1, 10, 1));
        final List<Optional<Integer>> optionalListValue = Arrays.asList(
                circularList.next(), circularList.next(),
                circularList.previous(), circularList.previous(), circularList.next());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testSimpleReset() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 1));
        final List<Optional<Integer>> optionalListValue = new ArrayList<>();

        optionalListValue.add(circularList.next());
        circularList.reset();
        optionalListValue.add(circularList.next());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testComplexReset() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 10, 9, 1, 2, 3));
        final List<Optional<Integer>> optionalListValue = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            optionalListValue.add(circularList.next());
        }
        circularList.reset();
        for(int i = 0; i < 3; i++) {
            optionalListValue.add(circularList.previous());
        }
        circularList.reset();
        for(int i = 0; i < 3; i++) {
            optionalListValue.add(circularList.next());
        }

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    /* Strategy test */
    @Test
    public void testNextEvenSimpleStrategy() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), new EvenStrategy());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testNextEvenCircularStrategy() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 2, 4, 6));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), new EvenStrategy());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testNextOddStrategy() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), new OddStrategy());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testMultipleOfTwoStrategy() {
        addElementsToCircularList(LIST_OF_NUMBER);
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), new MultipleOfTwoStrategy());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testEqualOfTwoStrategy() {
        addElementsToCircularList(List.of(2, 3, 6, 2, 5, 2, 3));
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 2, 2));
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), new EqualTwoStrategy());

        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }
}
