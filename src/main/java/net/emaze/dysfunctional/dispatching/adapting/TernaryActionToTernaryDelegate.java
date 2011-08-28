package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @author rferranti
 */
public class TernaryActionToTernaryDelegate<T1, T2, T3> implements TernaryDelegate<Void, T1, T2, T3> {

    private final TernaryAction<T1, T2, T3> adapted;

    public TernaryActionToTernaryDelegate(TernaryAction<T1, T2, T3> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null action");
        this.adapted = adapted;
    }

    @Override
    public Void perform(T1 first, T2 second, T3 third) {
        adapted.perform(first, second, third);
        return null;
    }
}
