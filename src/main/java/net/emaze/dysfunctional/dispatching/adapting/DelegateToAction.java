package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Adapts a delegate to an action. Adapting is performed by ignoring result of
 * the adapted delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T> the adapted delegate parameter type
 * @author rferranti
 */
public class DelegateToAction<R, T> implements Action<T> {

    private final Delegate<R, T> adapted;

    public DelegateToAction(Delegate<R, T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null delegate to action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T value) {
        adapted.perform(value);
    }
}
