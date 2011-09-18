package net.emaze.dysfunctional.dispatching.delegates;

/**
 * Provides an instance of T.
 * @param <T>
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
