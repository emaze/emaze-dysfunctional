package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class RangeOverlappingWith<T> implements Predicate<DenseRange<T>> {

    private final Range<T> other;

    public RangeOverlappingWith(Range<T> other) {
        this.other = other;
    }

    @Override
    public boolean accept(DenseRange<T> range) {
        return range.overlaps(other);
    }
}
