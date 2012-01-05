package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Adapts a unary action handling pairs to a binary action.
 *
 * @param <R> the result type parameter
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class UnaryToBinaryAction<T1, T2> implements BinaryAction<T1, T2> {

    private final Action<Pair<T1, T2>> action;

    public UnaryToBinaryAction(Action<Pair<T1, T2>> action) {
        dbc.precondition(action != null, "cannot create a UnaryToBinaryAction with a null Action");
        this.action = action;
    }

    @Override
    public void perform(T1 first, T2 second) {
        action.perform(Pair.of(first, second));
    }
}
