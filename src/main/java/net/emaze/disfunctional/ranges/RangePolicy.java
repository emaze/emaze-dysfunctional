package net.emaze.disfunctional.ranges;

/**
 * @author rferranti
 */
public interface RangePolicy<T extends Comparable<T>> {
    public Range<T> add(Range<T>... ranges);
    public Range<T> subtract(Range<T>... ranges);
    public Range<T> intersect(Range<T>... ranges);
}
