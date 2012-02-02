package net.emaze.dysfunctional.dispatching.delegates;

/**
 * Constantly returns a value.
 * @param <T> the provider return type
 * @author rferranti
 */
public class ConstantProvider<T> implements Provider<T> {

    private final T value;

    public ConstantProvider(T value) {
        this.value = value;
    }

    @Override
    public T provide() {
        return value;
    }
}
