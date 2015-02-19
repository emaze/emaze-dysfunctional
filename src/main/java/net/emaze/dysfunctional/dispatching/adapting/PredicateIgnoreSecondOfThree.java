package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Binary to ternary predicate adapter. Adapting is performed by ignoring the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @param <T3> the adapted predicate third parameter type
 * @author rferranti
 */
public class PredicateIgnoreSecondOfThree<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final BiPredicate<T1, T3> adapted;

    public PredicateIgnoreSecondOfThree(BiPredicate<T1, T3> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null binary predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return adapted.test(first, third);
    }
}
