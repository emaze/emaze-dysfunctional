package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Unary to binary predicate adapter. Adapting is performed by ignoring the
 * first parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class PredicateIgnoreFirst<T1, T2> implements BiPredicate<T1, T2> {

    private final Predicate<T2> adapted;

    public PredicateIgnoreFirst(Predicate<T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore first parameter of a null predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return adapted.test(second);
    }
}
