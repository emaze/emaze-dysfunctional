package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;


/**
 * Adapts a ternary action handling triples to an action handling triples.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class TernaryToUnaryAction<T1, T2, T3> implements Action<Triple<T1, T2, T3>> {

    private final TernaryAction<T1, T2, T3> action;

    public TernaryToUnaryAction(TernaryAction<T1, T2, T3> action) {
        dbc.precondition(action != null, "cannot create a TernaryToUnaryAction with a null TernaryAction");
        this.action = action;
    }

    @Override
    public void perform(Triple<T1, T2, T3> triple) {
        action.perform(triple.first(), triple.second(), triple.third());
    }
}
