package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @author rferranti
 */
public class ActionIgnoreThird<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final BinaryAction<T1, T2> action;

    public ActionIgnoreThird(BinaryAction<T1, T2> action) {
        dbc.precondition(action != null, "cannot ignore the third parameter with a null action");
        this.action = action;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        action.perform(first, second);
    }
}
