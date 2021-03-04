package lab01.tdd;

public class MultipleOfTwoStrategy implements SelectStrategy {

    private final static int MULTIPLE_OF = 2;

    @Override
    public boolean apply(int element) {
        return element % MULTIPLE_OF == 0 && element != 0;
    }
}
