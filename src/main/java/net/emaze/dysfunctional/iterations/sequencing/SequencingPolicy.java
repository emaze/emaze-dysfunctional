package net.emaze.dysfunctional.iterations.sequencing;

/**
 *
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

}
