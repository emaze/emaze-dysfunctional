package net.emaze.dysfunctional.order;

/**
 * Responsibility: defines a strategy to get the next of an element
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

}
