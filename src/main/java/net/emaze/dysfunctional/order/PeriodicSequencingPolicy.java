package net.emaze.dysfunctional.order;

import java.util.Optional;

/**
 * A periodic sequencing policy.
 *
 * @author rferranti
 * @param <T> the element type
 */
public class PeriodicSequencingPolicy<T> implements SequencingPolicy<T> {

    private final SequencingPolicy<T> inner;
    private final T lower;
    private final T upper;

    public PeriodicSequencingPolicy(SequencingPolicy<T> inner, T lower, T upper) {
        this.inner = inner;
        this.lower = lower;
        this.upper = upper;
    }

    @Override
    public Optional<T> next(T element) {
        return element.equals(upper) ? Optional.of(lower) : inner.next(element);
    }
}
