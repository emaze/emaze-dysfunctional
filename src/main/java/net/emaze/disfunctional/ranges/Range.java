package net.emaze.disfunctional.ranges;

/**
 *
 * @author rferranti
 */
public interface Range<T extends Comparable<T>> {
    boolean contains(T element);
    public T lower();
    public T upper();
}
