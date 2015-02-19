package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Adapts a binary action to an action handling pairs.
 * @param <R> the result type parameter
 * @param <T1> the former type parameter
 * @param <T2> the latter type parameter
 * @author rferranti
 */
public class BinaryToUnaryAction<T1, T2> implements Consumer<Pair<T1, T2>> {

    private final BinaryAction<T1, T2> action;

    public BinaryToUnaryAction(BinaryAction<T1, T2> action) {
        dbc.precondition(action != null, "cannot create a BinaryToUnaryAction with a null BinaryAction");
        this.action = action;
    }

    @Override
    public void accept(Pair<T1, T2> pair) {
        action.perform(pair.first(), pair.second());
    }
}
