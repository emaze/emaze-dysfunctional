package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Adapts a provider to a delegate. Adapting is performed by ignoring the
 * parameter passed to the adapted provider.
 *
 * @param <R> the adapter result type
 * @param <T> the adapter parameter type
 * @author rferranti
 */
public class IgnoreParameter<R, T> implements Delegate<R, T> {

    private final Provider<R> provider;

    public IgnoreParameter(Provider<R> provider) {
        dbc.precondition(provider != null, "cannot ignore parameter with a null provider");
        this.provider = provider;
    }

    @Override
    public R perform(T parameter) {
        return provider.provide();
    }
}
