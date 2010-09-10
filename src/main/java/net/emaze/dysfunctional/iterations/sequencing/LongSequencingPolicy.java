package net.emaze.dysfunctional.iterations.sequencing;

public class LongSequencingPolicy implements SequencingPolicy<Long> {

    @Override
    public Long next(Long element) {
        return ++element;
    }
}
