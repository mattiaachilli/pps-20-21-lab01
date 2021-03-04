package lab01.tdd;

public class EqualTwoStrategy implements SelectStrategy {
    protected final static int EQUAL_NUMBER = 2;


    @Override
    public boolean apply(int element) {
        return element == EQUAL_NUMBER;
    }
}
