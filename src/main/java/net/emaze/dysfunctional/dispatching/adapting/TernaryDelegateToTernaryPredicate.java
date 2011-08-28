package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 *
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class TernaryDelegateToTernaryPredicate<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final TernaryDelegate<Boolean, T1, T2, T3> adapted;

    public TernaryDelegateToTernaryPredicate(TernaryDelegate<Boolean, T1, T2, T3> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return adapted.perform(first, second, third);
    }
}
