package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Unary to binary predicate adapter. Adapting is performed by ignoring the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class PredicateIgnoreSecond<T1, T2> implements BiPredicate<T1, T2> {

    private final Predicate<T1> adapted;

    public PredicateIgnoreSecond(Predicate<T1> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return adapted.test(first);
    }
}
