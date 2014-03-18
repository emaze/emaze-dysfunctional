package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given provider, evaluated 
 * only once.
 *
 * @param <T> the provider parameter type
 */
public class MemoizingProvider<T> implements Provider<T> {

    private final Provider<T> provider;
    private T value;
    private boolean isNotEvaluated = true;

    public MemoizingProvider(Provider<T> provider) {
        dbc.precondition(provider != null, "Cannot create an only once provider with a null provider");
        this.provider = provider;
    }

    @Override
    public T provide() {
        if (isNotEvaluated) {
            value = provider.provide();
            isNotEvaluated = false;
        }
        return value;
    }
}
