package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter (rcurry)
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class ActionBinderThird<T1, T2, T3> implements BinaryAction<T1, T2> {

    private final TernaryAction<T1, T2, T3> action;
    private final T3 third;

    public ActionBinderThird(TernaryAction<T1, T2, T3> action, T3 third) {
        dbc.precondition(action != null, "cannot bind the third parameter of a null ternary action");
        this.action = action;
        this.third = third;
    }

    @Override
    public void perform(T1 first, T2 second) {
        action.perform(first, second, third);
    }
}
