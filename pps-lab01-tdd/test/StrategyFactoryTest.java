import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StrategyFactoryTest {

    private CircularList circularList;
    private StrategyFactory strategyFactory = new StrategyFactoryImpl();

    @BeforeEach
    public void init() {
        this.circularList = new CircularListImpl();
        CircularListTest.addElementsToCircularList(CircularListTest.LIST_OF_NUMBER, circularList);
    }

    @Test
    public void testAbstractFactoryNextEvenStrategy() {
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(2, 4, 6, 8, 10, 2, 4, 6));
        final SelectStrategy nextEvenStrategy = strategyFactory.createEvenStrategy();

        final List<Optional<Integer>> optionalListValue = CircularListTest.getAllNextOptionalList(exceptedList.size(), nextEvenStrategy, circularList);


        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }

    @Test
    public void testAbstractFactoryNextOddStrategy() {
        final List<Integer> exceptedList = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9));
        final SelectStrategy nextOddStrategy = strategyFactory.createOddStrategy();

        final List<Optional<Integer>> optionalListValue = CircularListTest.getAllNextOptionalList(exceptedList.size(), nextOddStrategy, circularList);


        for (int i = 0; i < exceptedList.size(); i++) {
            assertFalse(optionalListValue.get(i).isEmpty());
            assertEquals(exceptedList.get(i), optionalListValue.get(i).get());
        }
    }
}
