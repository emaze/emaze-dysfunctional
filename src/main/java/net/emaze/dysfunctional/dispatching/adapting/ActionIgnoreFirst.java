package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Adapts an action to a binary action. Adapting is performed by ignoring the
 * first parameter passed to the adapted action.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @author rferranti
 */
public class ActionIgnoreFirst<T1, T2> implements BinaryAction<T1, T2> {

    private final Action<T2> action;

    public ActionIgnoreFirst(Action<T2> action) {
        dbc.precondition(action != null, "cannot ignore the first parameter with a null action");
        this.action = action;
    }

    @Override
    public void perform(T1 first, T2 second) {
        action.perform(second);
    }
}
