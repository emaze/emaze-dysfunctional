package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Adapts a ternary delegate with Boolean result type to a ternary predicate.
 *
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
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
