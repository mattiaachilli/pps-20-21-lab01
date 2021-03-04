package lab01.tdd;

public class EqualOfStrategy implements SelectStrategy {
    private final int equalNumber;

    public EqualOfStrategy(final int equalNumber) {
        this.equalNumber = equalNumber;
    }

    @Override
    public boolean apply(int element) {
        return element == this.equalNumber;
    }
}
