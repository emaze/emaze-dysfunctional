package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

public class TransformingProvider<R, T> implements Provider<R> {

    private final Delegate<R, T> transformer;
    private final Provider<T> provider;

    public TransformingProvider(Delegate<R, T> transformer, Provider<T> provider) {
        dbc.precondition(transformer != null, "cannot compose provider with a null transformer");
        dbc.precondition(provider != null, "cannot compose transformer with a null provider");
        this.transformer = transformer;
        this.provider = provider;
    }

    @Override
    public R provide() {
        return transformer.perform(provider.provide());
    }
}
