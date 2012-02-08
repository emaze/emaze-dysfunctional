package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 * Unary to nullary action adapter. Adapting is performed by currying the
 * parameter of the adapted action.
 *
 * @param <T> the adapted action parameter type
 * @author rferranti
 */
public class ActionBinder<T> implements Runnable {

    private final Action<T> action;
    private final T only;

    public ActionBinder(Action<T> action, T only) {
        dbc.precondition(action != null, "cannot bind the parameter of a null action");
        this.action = action;
        this.only = only;
    }

    @Override
    public void run() {
        action.perform(only);
    }
}
