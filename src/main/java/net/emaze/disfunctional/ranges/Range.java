package net.emaze.disfunctional.ranges;

/**
 *
 * @author rferranti
 */
public interface Range<T extends Comparable<T>> extends Iterable<T>, Comparable<Range<T>> {
    boolean contains(T element);
    public T lower();
    public T upper();
}
