package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Adapts a ternary predicate to a ternary delegate with Boolean result type.
 *
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @author rferranti
 */
public class TernaryPredicateToTernaryDelegate<T1, T2, T3> implements TriFunction<T1, T2, T3, Boolean> {

    private final TernaryPredicate<T1, T2, T3> adapted;

    public TernaryPredicateToTernaryDelegate(TernaryPredicate<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary predicate to ternary delegate");
        this.adapted = adaptee;
    }

    @Override
    public Boolean apply(T1 first, T2 second, T3 third) {
        return adapted.accept(first, second, third);
    }
}
