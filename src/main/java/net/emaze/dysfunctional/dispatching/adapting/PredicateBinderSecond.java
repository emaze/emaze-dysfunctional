package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import java.util.function.Predicate;

/**
 * Binary to unary predicate adapter. Adapting is performed by currying the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class PredicateBinderSecond<T1, T2> implements Predicate<T1> {

    private final BinaryPredicate<T1, T2> adapted;
    private final T2 second;

    public PredicateBinderSecond(BinaryPredicate<T1, T2> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null binary predicate");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public boolean test(T1 first) {
        return adapted.accept(first, second);
    }
}
