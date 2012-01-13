package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 * Unary to nullary action adapter
 *
 * @param <R> the return Type
 * @param <T> the only element Type
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
