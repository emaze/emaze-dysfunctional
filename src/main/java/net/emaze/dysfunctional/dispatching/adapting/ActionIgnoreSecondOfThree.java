package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Adapts a binary action to a ternary action. Adapting is performed by ignoring
 * the second parameter passed to the adapted action.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class ActionIgnoreSecondOfThree<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final BinaryAction<T1, T3> adapted;

    public ActionIgnoreSecondOfThree(BinaryAction<T1, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        adapted.perform(first, third);
    }
}
