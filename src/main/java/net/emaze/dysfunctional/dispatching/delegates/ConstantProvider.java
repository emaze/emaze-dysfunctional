package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;

/**
 * Constantly returns a value.
 *
 * @param <T> the provider result type
 * @author rferranti
 */
public class ConstantProvider<T> implements Supplier<T> {

    private final T value;

    public ConstantProvider(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }
}
