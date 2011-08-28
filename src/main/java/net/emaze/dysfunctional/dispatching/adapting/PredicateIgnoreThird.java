package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * @param <T1>
 * @param <T2>
 * @param <T3>
 * @author rferranti
 */
public class PredicateIgnoreThird<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final BinaryPredicate<T1, T2> predicate;

    public PredicateIgnoreThird(BinaryPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot ignore the third parameter with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return predicate.accept(first, second);
    }
}
