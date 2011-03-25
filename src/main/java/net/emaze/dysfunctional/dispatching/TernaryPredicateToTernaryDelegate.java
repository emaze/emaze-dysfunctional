package net.emaze.dysfunctional.dispatching;

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
public class TernaryPredicateToTernaryDelegate<T1, T2, T3> implements TernaryDelegate<Boolean, T1, T2, T3> {

    private final TernaryPredicate<T1, T2, T3> adapted;

    public TernaryPredicateToTernaryDelegate(TernaryPredicate<T1, T2, T3> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null predicate");
        this.adapted = adapted;
    }

    @Override
    public Boolean perform(T1 first, T2 second, T3 third) {
        return adapted.test(first, second, third);
    }
}
