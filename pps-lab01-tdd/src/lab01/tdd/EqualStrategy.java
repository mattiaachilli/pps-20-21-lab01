package lab01.tdd;

public class EqualStrategy implements SelectStrategy {
    private int equalNumber;

    public EqualStrategy(final int equalNumber) {
        this.equalNumber = equalNumber;
    }

    @Override
    public boolean apply(int element) {
        return element == this.equalNumber;
    }
}
