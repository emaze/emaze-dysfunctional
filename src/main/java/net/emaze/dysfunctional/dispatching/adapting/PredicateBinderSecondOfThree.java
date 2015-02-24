package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Ternary to binary predicate adapter. Adapting is performed by currying the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first element Type
 * @param <T2> the adapted predicate second element Type
 * @param <T3> the adapted predicate third element type
 * @author rferranti
 */
public class PredicateBinderSecondOfThree<T1, T2, T3> implements BiPredicate<T1, T3> {

    private final TriPredicate<T1, T2, T3> adapted;
    private final T2 second;

    public PredicateBinderSecondOfThree(TriPredicate<T1, T2, T3> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null ternary predicate");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public boolean test(T1 first, T3 third) {
        return adapted.test(first, second, third);
    }
}
