package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter. Adapting is performed by currying the
 * second parameter of the adapted ternary action.
 *
 * @param <T1> the adapted action first element Type
 * @param <T2> the adapted action second element Type
 * @param <T3> the adapted action third element type
 * @author rferranti
 */
public class ActionBinderSecondOfThree<T1, T2, T3> implements BinaryAction<T1, T3> {

    private final TernaryAction<T1, T2, T3> adapted;
    private final T2 second;

    public ActionBinderSecondOfThree(TernaryAction<T1, T2, T3> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null ternary action");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public void perform(T1 first, T3 third) {
        adapted.perform(first, second, third);
    }
}
