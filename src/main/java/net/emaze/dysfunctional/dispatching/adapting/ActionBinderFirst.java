package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Binary to unary action adapter. Adapting is performed by currying the first
 * parameter of the adapted binary action.
 *
 * @param <T1> the adapted action former element Type
 * @param <T2> the adapted action latter element Type
 * @author rferranti
 */
public class ActionBinderFirst<T1, T2> implements Action<T2> {

    private final BinaryAction<T1, T2> action;
    private final T1 first;

    public ActionBinderFirst(BinaryAction<T1, T2> action, T1 first) {
        dbc.precondition(action != null, "cannot bind the first parameter of a null binary action");
        this.action = action;
        this.first = first;
    }

    @Override
    public void perform(T2 second) {
        action.perform(first, second);
    }
}
