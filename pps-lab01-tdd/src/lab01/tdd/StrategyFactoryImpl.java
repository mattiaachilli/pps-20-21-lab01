package lab01.tdd;

/*
Implementation StrategyFactory
 */
public class StrategyFactoryImpl implements StrategyFactory {
    @Override

    public SelectStrategy createEvenStrategy() {
        return new EvenStrategy();
    }

    @Override
    public SelectStrategy createOddStrategy() {
        return new OddStrategy();
    }

    @Override
    public SelectStrategy createMultipleOfStrategy(final int number) {
        return new MultipleOfStrategy(number);
    }

    @Override
    public SelectStrategy createEqualOfStrategy(final int number) {
        return new EqualOfStrategy(number);
    }
}
