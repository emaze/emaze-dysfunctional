package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class ActionBinderSecondOfThree<T1, T2, T3> implements BinaryAction<T1, T3> {

    private final TernaryAction<T1, T2, T3> action;
    private final T2 second;

    public ActionBinderSecondOfThree(TernaryAction<T1, T2, T3> action, T2 second) {
        dbc.precondition(action != null, "cannot bind the second parameter of a null ternary action");
        this.action = action;
        this.second = second;
    }

    @Override
    public void perform(T1 first, T3 third) {
        action.perform(first, second, third);
    }
}
