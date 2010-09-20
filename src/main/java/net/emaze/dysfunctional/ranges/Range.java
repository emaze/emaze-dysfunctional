package net.emaze.dysfunctional.ranges;

/**
 *
 * @author rferranti
 */
public interface Range<T> extends Iterable<T>, Comparable<Range<T>> {
    boolean contains(T element);
    boolean overlaps(Range<T> rhs);
    public T lower();
    public T upper();
}
