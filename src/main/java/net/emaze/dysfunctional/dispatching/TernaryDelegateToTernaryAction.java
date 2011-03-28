package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class TernaryDelegateToTernaryAction<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final TernaryDelegate<?, T1, T2, T3> adapted;

    public TernaryDelegateToTernaryAction(TernaryDelegate<?, T1, T2, T3> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        adapted.perform(first, second, third);
    }
}
