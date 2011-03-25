package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class ActionToDelegate<T> implements Delegate<Void, T> {

    private final Action<T> adapted;

    public ActionToDelegate(Action<T> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null action");
        this.adapted = adapted;
    }

    @Override
    public Void perform(T value) {
        adapted.perform(value);
        return null;
    }
}
