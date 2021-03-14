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
        if (this.size() > 0) {
            return this.next(element -> true);
        }
        return optionalInteger;
    }

    private void decrementIndex() {
        if (this.index - 1 < 0) { //Fix index
            this.index = circularList.size() - 1;
        } else {
            this.index--;
        }
    }

    @Override
    public Optional<Integer> previous() {
        Optional<Integer> optionalInteger = Optional.empty();
        if (this.size() > 0) {
            if(this.firstTime) { //If first time return the first element
                this.firstTime = false;
            } else {
                this.decrementIndex();
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
    public Optional<Integer> next(final SelectStrategy strategy) {
        Optional<Integer> optionalInteger = Optional.empty();
        if (this.size() > 0) {
            if(this.index > circularList.size() - 1) { //Fix index
                this.index = 0;
            }
            for (int i = this.index; i < circularList.size(); i++) { //Resumes from previous index
                if (strategy.apply(circularList.get(i))) {
                    optionalInteger = Optional.of(circularList.get(i));
                    this.index = i + 1; //Update index
                    return optionalInteger;
                }
            }
            if (this.index != 0) { //The element existed so it's necessary to recall method
                this.index = 0;
                return next(strategy);
            }
        }
        return optionalInteger;
    }
}
