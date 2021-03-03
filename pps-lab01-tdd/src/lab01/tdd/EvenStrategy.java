package lab01.tdd;

public class EvenStrategy implements SelectStrategy {
    private final static int EVEN = 2;

    @Override
    public boolean apply(int element) {
        return element % EVEN == 0;
    }
}
