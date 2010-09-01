package net.emaze.dysfunctional.iterations.sequencing;

public class LongSequencingPolicy implements SequencingPolicy<Long> {

    public Long next(Long element) {
        return ++element;
    }
}
