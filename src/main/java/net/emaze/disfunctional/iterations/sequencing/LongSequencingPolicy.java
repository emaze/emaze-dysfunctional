package net.emaze.disfunctional.iterations.sequencing;

public class LongSequencingPolicy implements SequencingPolicy<Long> {

    public Long next(Long element) {
        return ++element;
    }
}
