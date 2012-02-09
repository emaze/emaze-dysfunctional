package net.emaze.dysfunctional.order;

/**
 * Responsibility: defines a strategy to get the next and previous of an
 * element.
 *
 * @param <T> the sequenced element type
 * @author rferranti
 */
public interface SequencingPolicy<T> {

    T next(T element);

    T prev(T prev);
}
