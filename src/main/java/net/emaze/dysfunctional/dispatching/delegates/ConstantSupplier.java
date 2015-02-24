package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;

/**
 * Constantly returns a value.
 *
 * @param <T> the supplier result type
 * @author rferranti
 */
public class ConstantSupplier<T> implements Supplier<T> {

    private final T value;

    public ConstantSupplier(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }
}
