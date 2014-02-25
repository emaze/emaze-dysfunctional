package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that evaluate the value for the given provider only once.
 * Useful to implement lazy evaluation.
 *
 * @param <T> the provider parameter type
 */
public class OnlyOnceProvider<T> implements Provider<T> {

    private final Provider<T> provider;
    private T value = null;

    public OnlyOnceProvider(Provider<T> provider) {
        dbc.precondition(provider != null, "Cannot create an only once provider with a null provider");
        this.provider = provider;
    }

    @Override
    public T provide() {
        if (value == null) {
            value = provider.provide();
        }
        return value;
    }
}
