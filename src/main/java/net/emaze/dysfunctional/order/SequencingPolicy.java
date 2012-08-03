package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.options.Maybe;

/**
 * Responsibility: defines a strategy to get the next and previous of an
 * element.
 *
 * @param <T> the sequenced element type
 * @author rferranti
 */
public interface SequencingPolicy<T> {

    Maybe<T> next(T element);

    T prev(T prev); // TODO: maybe too
}
