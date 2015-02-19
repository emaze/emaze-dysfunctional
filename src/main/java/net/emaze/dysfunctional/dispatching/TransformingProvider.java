package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Composes a delegate with a provider (delegate ° provider).
 *
 * @author rferranti
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 */
public class TransformingProvider<R, T> implements Provider<R> {

    private final Function<T, R> transformer;
    private final Provider<T> provider;

    public TransformingProvider(Function<T, R> transformer, Provider<T> provider) {
        dbc.precondition(transformer != null, "cannot compose provider with a null transformer");
        dbc.precondition(provider != null, "cannot compose transformer with a null provider");
        this.transformer = transformer;
        this.provider = provider;
    }

    @Override
    public R provide() {
        return transformer.apply(provider.provide());
    }
}
