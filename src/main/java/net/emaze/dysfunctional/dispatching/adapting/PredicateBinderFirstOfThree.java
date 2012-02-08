package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Ternary to binary predicate adapter. Adapting is performed by currying the
 * first parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @param <T3> the adapted predicate third parameter type
 * @author rferranti
 */
public class PredicateBinderFirstOfThree<T1, T2, T3> implements BinaryPredicate<T2, T3> {

    private final TernaryPredicate<T1, T2, T3> adapted;
    private final T1 first;

    public PredicateBinderFirstOfThree(TernaryPredicate<T1, T2, T3> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null ternary predicate");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public boolean accept(T2 second, T3 third) {
        return adapted.accept(first, second, third);
    }
}
