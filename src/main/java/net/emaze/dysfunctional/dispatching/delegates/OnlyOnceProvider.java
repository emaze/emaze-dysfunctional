package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A lazy proxy that evaluate the value for the given provider only once.
 *
 * @param <T> the provider parameter type
 */
public class OnlyOnceProvider<T> implements Provider<T> {

    private final Provider<T> provider;
    private T value = null;

    public OnlyOnceProvider(Provider<T> provider) {
        dbc.precondition(provider != null, "Cannot create a proxy provider with a null provider");
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
