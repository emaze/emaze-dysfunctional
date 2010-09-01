package net.emaze.dysfunctional.iterations.sequencing;

public class IntegerSequencingPolicy implements SequencingPolicy<Integer> {

    public Integer next(Integer element) {
        return ++element;
    }
}
