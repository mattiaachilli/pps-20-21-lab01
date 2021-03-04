package lab01.tdd;

/*
 A factory of strategy.
 */
public interface StrategyFactory {
    public SelectStrategy createEvenStrategy();

    public SelectStrategy createOddStrategy();

    public SelectStrategy createMultipleOfStrategy(final int number);

    public SelectStrategy createEqualStrategy(final int number);
}
