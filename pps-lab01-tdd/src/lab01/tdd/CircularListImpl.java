package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> circularList;
    private int index;
    private boolean firstTime;

    public CircularListImpl() {
        this.circularList = new ArrayList<>();
        this.index = 0;
        this.firstTime = true;
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
            if (this.firstTime) {
                this.firstTime = false;
            } else {
                if (this.index + 1 == circularList.size()) {
                    this.index = 0;
                } else {
                    this.index++;
                }
            }
            optionalInteger = Optional.of(circularList.get(this.index));
        }
        return optionalInteger;
    }

    @Override
    public Optional<Integer> previous() {
        Optional<Integer> optionalInteger = Optional.empty();
        if (size() > 0) {
            if(this.firstTime) {
                this.firstTime = false;
            } else {
                if (this.index - 1 < 0) {
                    this.index = circularList.size() - 1;
                } else {
                    this.index--;
                }
            }
            optionalInteger = Optional.of(circularList.get(this.index));
        }
        return optionalInteger;
    }

    @Override
    public void reset() {
        this.index = 0;
        this.firstTime = true;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        return Optional.empty();
    }
}
