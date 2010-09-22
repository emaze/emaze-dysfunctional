package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class RangeNotContaining<T> implements Predicate<DenseRange<T>>{

    private final T element;

    public RangeNotContaining(T element) {
        this.element = element;
    }

    @Override
    public boolean test(DenseRange<T> range) {
        return range.contains(element);
    }

}
