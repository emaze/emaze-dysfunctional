package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Binary to unary action adapter (rcurry)
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public class ActionBinderSecond<T1, T2> implements Action<T1> {

    private final BinaryAction<T1, T2> action;
    private final T2 second;

    public ActionBinderSecond(BinaryAction<T1, T2> action, T2 second) {
        dbc.precondition(action != null, "cannot bind the second parameter of a null binary action");
        this.action = action;
        this.second = second;
    }

    @Override
    public void perform(T1 first) {
        action.perform(first, second);
    }
}
