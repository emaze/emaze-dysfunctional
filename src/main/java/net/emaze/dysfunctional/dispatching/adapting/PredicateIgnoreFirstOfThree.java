package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Binary to ternary predicate adapter. Adapting is performed by ignoring the
 * first parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @param <T3> the adapted predicate third parameter type
 * @author rferranti
 */
public class PredicateIgnoreFirstOfThree<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final BinaryPredicate<T2, T3> predicate;

    public PredicateIgnoreFirstOfThree(BinaryPredicate<T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot ignore the first parameter with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return predicate.accept(second, third);
    }
}
