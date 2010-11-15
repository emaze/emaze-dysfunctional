package net.emaze.dysfunctional.order;

/**
 * Responsibility: defines a strategy to get the next of an element
 * @param <T> 
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);
    T prev(T prev);
}
