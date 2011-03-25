package net.emaze.dysfunctional.dispatching.actions.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter (curry)
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class ActionBinderFirstOfThree<T1, T2, T3> implements BinaryAction<T2, T3> {

    private final TernaryAction<T1, T2, T3> action;
    private final T1 first;

    public ActionBinderFirstOfThree(TernaryAction<T1, T2, T3> action, T1 first) {
        dbc.precondition(action != null, "cannot bind the first parameter of a null ternary action");
        this.action = action;
        this.first = first;
    }

    @Override
    public void perform(T2 second, T3 third) {
        action.perform(first, second, third);
    }
}
