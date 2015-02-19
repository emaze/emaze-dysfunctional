package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Composes a delegate with a provider (delegate ° provider).
 *
 * @author rferranti
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 */
public class TransformingProvider<R, T> implements Supplier<R> {

    private final Function<T, R> transformer;
    private final Supplier<T> provider;

    public TransformingProvider(Function<T, R> transformer, Supplier<T> provider) {
        dbc.precondition(transformer != null, "cannot compose provider with a null transformer");
        dbc.precondition(provider != null, "cannot compose transformer with a null provider");
        this.transformer = transformer;
        this.provider = provider;
    }

    @Override
    public R get() {
        return transformer.apply(provider.get());
    }
}
