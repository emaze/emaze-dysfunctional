package net.emaze.disfunctional.ranges;

/**
 *
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);
}
