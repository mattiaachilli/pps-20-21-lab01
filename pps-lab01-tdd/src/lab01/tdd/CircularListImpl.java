package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> circularList;
    private int index;
    private boolean first;

    public CircularListImpl() {
        this.circularList = new ArrayList<>();
        this.index = 0;
        this.first = true;
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
            this.index = (this.index + 1) % size();
        }
        return optionalInteger;
    }

    @Override
    public Optional<Integer> previous() {
        Optional<Integer> optionalInteger = Optional.empty();
        if (size() > 0) {
            optionalInteger = Optional.of(circularList.get(this.index));
            this.index = this.index - 1 < 0 ? circularList.size() - 1 : --this.index;
        }
        return optionalInteger;
    }

    @Override
    public void reset() {

    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
