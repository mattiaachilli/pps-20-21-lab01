package lab01.tdd;

public class MultipleOfStrategy implements SelectStrategy {

    private final int multipleNumber;

    public MultipleOfStrategy(final int multipleNumber) {
        this.multipleNumber = multipleNumber;
    }

    @Override
    public boolean apply(int element) {
        return element % this.multipleNumber == 0 && element != 0;
    }
}
