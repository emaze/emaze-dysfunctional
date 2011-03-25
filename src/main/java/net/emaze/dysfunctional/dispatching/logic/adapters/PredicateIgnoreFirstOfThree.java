package net.emaze.dysfunctional.dispatching.logic.adapters;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class PredicateIgnoreFirstOfThree<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final BinaryPredicate<T2, T3> predicate;

    public PredicateIgnoreFirstOfThree(BinaryPredicate<T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot ignore the first parameter with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        return predicate.test(second, third);
    }
}
