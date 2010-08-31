package net.emaze.disfunctional.iterations.sequencing;

/**
 *
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

}
