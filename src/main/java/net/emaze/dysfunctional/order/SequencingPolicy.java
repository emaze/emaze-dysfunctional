package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.options.Maybe;

/**
 * Responsibility: defines a strategy to get the next of an element.
 *
 * @param <T> the sequenced element type
 * @author rferranti
 */
public interface SequencingPolicy<T> {

    Maybe<T> next(T element);
}
