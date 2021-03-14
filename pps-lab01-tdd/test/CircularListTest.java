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
    private final static int NUMBER_THREE_FOR_STRATEGY = 3;
    protected final static List<Integer> LIST_OF_NUMBER = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private final StrategyFactory strategyFactory = new StrategyFactoryImpl();

    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
    }

    private void addElementsToCircularList() {
        for (final int element : CircularListTest.LIST_OF_NUMBER) {
            this.circularList.add(element);
        }
    }

    private List<Optional<Integer>> getAllNextOptionalList(final int numberElements, final SelectStrategy strategy) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        if (numberElements > 0) {
            for (int i = 0; i < numberElements; i++) {
                final Optional<Integer> optionalInteger = strategy != null ?
                        this.circularList.next(strategy) : this.circularList.next();
                optionalValue.add(optionalInteger);
            }
        }
        return optionalValue;
    }

    private List<Optional<Integer>> getAllPreviousOptionalList(final int numberElements) {
        final List<Optional<Integer>> optionalValue = new ArrayList<>();
        if (numberElements > 0) {
            for (int i = 0; i < numberElements; i++) {
                optionalValue.add(this.circularList.previous());
            }
        }
        return optionalValue;
    }

    private void checkOutputWithExceptedList(final List<Integer> exceptedList, final List<Optional<Integer>> optionalList) {
        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalList.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalList.get(i).get());
        }
    }

    @Test
    public void testAddAndSize() {
        addElementsToCircularList();
        assertEquals(circularList.size(), LIST_OF_NUMBER.size());
    }

    @Test
    public void testEmpty() {
        assertTrue(circularList.isEmpty());
        addElementsToCircularList();
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testNext() {
        addElementsToCircularList();
        final List<Integer> exceptedList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3);
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), null);

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    @Test
    public void testPrevious() {
        addElementsToCircularList();
        final List<Integer> exceptedList = Arrays.asList(1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 10);
        final List<Optional<Integer>> optionalListValue = this.getAllPreviousOptionalList(exceptedList.size());

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    @Test
    public void testEmptyNextPrevious() {
        assertTrue(circularList.next().isEmpty());
        assertTrue(circularList.previous().isEmpty());
    }

    @Test
    public void testReset() {
        addElementsToCircularList();
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 1, 1));
        final List<Optional<Integer>> optionalListValue = new ArrayList<>();

        optionalListValue.add(circularList.next());
        circularList.reset();
        optionalListValue.add(circularList.next());
        circularList.reset();
        optionalListValue.add(circularList.previous());

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    /* Test with strategy */
    @Test
    public void testNextEvenStrategy() {
        addElementsToCircularList();
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 2, 4, 6)); //Only even numbers
        final SelectStrategy nextEvenStrategy = strategyFactory.createEvenStrategy();
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), nextEvenStrategy);

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    @Test
    public void testNextOddStrategy() {
        addElementsToCircularList();
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9)); //Only odd numbers
        final SelectStrategy nextOddStrategy = strategyFactory.createOddStrategy();
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), nextOddStrategy);

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    @Test
    public void testNextMultipleOfStrategy() {
        addElementsToCircularList();
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(3, 6, 9)); //Only multiple of 3
        final SelectStrategy nextMultipleOfStrategy = strategyFactory.createMultipleOfStrategy(NUMBER_THREE_FOR_STRATEGY);
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), nextMultipleOfStrategy);

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }

    @Test
    public void testNextEqualOfStrategy() {
        addElementsToCircularList();
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(3, 3));
        final SelectStrategy nextEqualOfStrategy = strategyFactory.createEqualOfStrategy(NUMBER_THREE_FOR_STRATEGY);
        final List<Optional<Integer>> optionalListValue = this.getAllNextOptionalList(exceptedList.size(), nextEqualOfStrategy);

        this.checkOutputWithExceptedList(exceptedList, optionalListValue);
    }
}
