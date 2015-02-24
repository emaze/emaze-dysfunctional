package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Adapts a ternary predicate to a ternary function with Boolean result type.
 *
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @param <T3> the adapted function third parameter type
 * @author rferranti
 */
public class TernaryPredicateToFunction<T1, T2, T3> implements TriFunction<T1, T2, T3, Boolean> {

    private final TriPredicate<T1, T2, T3> adapted;

    public TernaryPredicateToFunction(TriPredicate<T1, T2, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null ternary predicate to ternary function");
        this.adapted = adaptee;
    }

    @Override
    public Boolean apply(T1 first, T2 second, T3 third) {
        return adapted.test(first, second, third);
    }
}
