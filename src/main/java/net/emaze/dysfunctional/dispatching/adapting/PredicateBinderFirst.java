package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

/**
 * Binary to unary predicate adapter. Adapting is performed by currying the
 * first parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second element type
 * @author rferranti
 */
public class PredicateBinderFirst<T1, T2> implements Predicate<T2> {

    private final BiPredicate<T1, T2> adapted;
    private final T1 first;

    public PredicateBinderFirst(BiPredicate<T1, T2> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null binary predicate");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public boolean test(T2 second) {
        return adapted.test(first, second);
    }
}
