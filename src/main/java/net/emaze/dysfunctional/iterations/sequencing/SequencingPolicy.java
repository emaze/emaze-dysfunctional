package net.emaze.dysfunctional.iterations.sequencing;

/**
 * Responsibility: defines a strategy to get the next of an element
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

}
