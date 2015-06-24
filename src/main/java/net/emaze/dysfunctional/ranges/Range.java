package net.emaze.dysfunctional.ranges;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>
 * @author rferranti
 */
public interface Range<T> extends Iterable<T>, Comparable<Range<T>> {

    public static enum Endpoint {

        Include, Exclude
    };

    boolean contains(T element);

    boolean overlaps(Range<T> rhs);

    T begin();

    Optional<T> end();

    List<DenseRange<T>> densified();
}
