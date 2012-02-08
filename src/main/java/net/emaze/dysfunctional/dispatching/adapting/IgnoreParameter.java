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

    private final Provider<R> adapted;

    public IgnoreParameter(Provider<R> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore parameter of a null provider");
        this.adapted = adaptee;
    }

    @Override
    public R perform(T parameter) {
        return adapted.provide();
    }
}
