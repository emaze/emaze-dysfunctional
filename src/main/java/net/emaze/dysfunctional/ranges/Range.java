package net.emaze.dysfunctional.ranges;

import java.util.List;
import net.emaze.dysfunctional.options.Maybe;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public interface Range<T> extends Iterable<T>, Comparable<Range<T>> {
    boolean contains(T element);
    boolean overlaps(Range<T> rhs);
    T first();
    Maybe<T> afterLast();
    List<DenseRange<T>> densified();
}
