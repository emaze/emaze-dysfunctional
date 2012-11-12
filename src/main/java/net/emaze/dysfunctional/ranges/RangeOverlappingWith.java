package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class RangeOverlappingWith<R extends Range<T>, T> implements Predicate<R> {

    private final Range<T> other;

    public RangeOverlappingWith(Range<T> other) {
        this.other = other;
    }

    @Override
    public boolean accept(R range) {
        return range.overlaps(other);
    }
}
