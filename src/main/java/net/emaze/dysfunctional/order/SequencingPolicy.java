package net.emaze.dysfunctional.order;

import java.util.Optional;

/**
 * Responsibility: defines a strategy to get the next of an element.
 *
 * @param <T> the sequenced element type
 * @author rferranti
 */
public interface SequencingPolicy<T> {

    Optional<T> next(T element);
}
