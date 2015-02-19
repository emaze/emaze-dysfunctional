package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A proxy that returns the value provided by the given provider, evaluated 
 * only once.
 *
 * @param <T> the provider parameter type
 */
public class MemoizingProvider<T> implements Supplier<T> {

    private final Supplier<T> provider;
    private T value;
    private boolean isNotEvaluated = true;

    public MemoizingProvider(Supplier<T> provider) {
        dbc.precondition(provider != null, "Cannot create an only once provider with a null provider");
        this.provider = provider;
    }

    @Override
    public T get() {
        if (isNotEvaluated) {
            value = provider.get();
            isNotEvaluated = false;
        }
        return value;
    }
}
