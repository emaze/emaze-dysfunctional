package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Ternary to binary predicate adapter. Adapting is performed by currying the
 * third parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first element Type
 * @param <T2> the adapted predicate second element Type
 * @param <T3> the adapted predicate third element type
 * @author rferranti
 */
public class PredicateBinderThird<T1, T2, T3> implements BinaryPredicate<T1, T2> {

    private final TernaryPredicate<T1, T2, T3> adapted;
    private final T3 third;

    public PredicateBinderThird(TernaryPredicate<T1, T2, T3> adaptee, T3 third) {
        dbc.precondition(adaptee != null, "cannot bind third parameter of a null ternary predicate");
        this.adapted = adaptee;
        this.third = third;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return adapted.accept(first, second, third);
    }
}
