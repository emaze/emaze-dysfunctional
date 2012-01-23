package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class DelegateToAction<R, T> implements Action<T> {

    private final Delegate<R, T> adapted;

    public DelegateToAction(Delegate<R, T> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public void perform(T value) {
        adapted.perform(value);
    }
}
