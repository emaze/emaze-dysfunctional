package net.emaze.dysfunctional.ranges;

import java.util.List;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public interface Range<T> extends Iterable<T>, Comparable<Range<T>> {
    boolean contains(T element);
    boolean overlaps(Range<T> rhs);
    T lower();
    T upper();
    List<DenseRange<T>> densified();
}
