package net.emaze.dysfunctional.dispatching.logic.adapters;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Ternary to binary predicate adapter (rcurry)
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class PredicateBinderThird<T1, T2, T3> implements BinaryPredicate<T1, T2> {

    private final TernaryPredicate<T1, T2, T3> predicate;
    private final T3 third;

    public PredicateBinderThird(TernaryPredicate<T1, T2, T3> predicate, T3 third) {
        dbc.precondition(predicate != null, "cannot bind the third parameter of a null ternary predicate");
        this.predicate = predicate;
        this.third = third;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return predicate.test(first, second, third);
    }
}
