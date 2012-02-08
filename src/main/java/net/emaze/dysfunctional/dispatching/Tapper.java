package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Applies an action to an element and returns the (possibly modified) element.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class Tapper<T> implements Delegate<T, T> {

    private final Action<T> action;

    public Tapper(Action<T> action) {
        dbc.precondition(action != null, "cannot create a Tapper with a null action");
        this.action = action;
    }

    @Override
    public T perform(T t) {
        action.perform(t);
        return t;
    }
}
