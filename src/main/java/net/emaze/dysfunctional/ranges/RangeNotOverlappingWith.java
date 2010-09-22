package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class RangeNotOverlappingWith<T> implements Predicate<DenseRange<T>> {

    private final Range<T> other;

    public RangeNotOverlappingWith(Range<T> other) {
        this.other = other;
    }

    @Override
    public boolean test(DenseRange<T> range) {
        return range.overlaps(other);
    }
}
