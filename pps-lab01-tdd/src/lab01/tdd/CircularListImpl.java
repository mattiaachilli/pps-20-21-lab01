package lab01.tdd;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> circularList;
    private int index;

    public CircularListImpl() {
        this.circularList = new ArrayList<>();
        this.index = 0;
    }

    @Override
    public void add(int element) {
        this.circularList.add(element);
    }

    @Override
    public int size() {
        return this.circularList.size();
    }

    @Override
    public boolean isEmpty() {
        return circularList.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        Optional<Integer> optionalInteger = Optional.empty();
        if (size() > 0) {
            optionalInteger = Optional.of(circularList.get(this.index));
            this.index = (index + 1) % size();
        }
        return optionalInteger;
    }

    @Override
    public Optional<Integer> previous() {
        return Optional.empty();
    }

    @Override
    public void reset() {

    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
