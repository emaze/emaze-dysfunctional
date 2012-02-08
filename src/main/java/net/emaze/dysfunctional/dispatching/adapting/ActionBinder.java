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

    private final Action<T> adapted;
    private final T only;

    public ActionBinder(Action<T> adaptee, T only) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null action");
        this.adapted = adaptee;
        this.only = only;
    }

    @Override
    public void run() {
        adapted.perform(only);
    }
}
