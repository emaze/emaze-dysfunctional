package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Ternary to binary predicate adapter
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class PredicateBinderSecondOfThree<T1, T2, T3> implements BinaryPredicate<T1, T3> {

    private final TernaryPredicate<T1, T2, T3> predicate;
    private final T2 second;

    public PredicateBinderSecondOfThree(TernaryPredicate<T1, T2, T3> predicate, T2 second) {
        dbc.precondition(predicate != null, "cannot bind the second parameter of a null ternary predicate");
        this.predicate = predicate;
        this.second = second;
    }

    @Override
    public boolean accept(T1 first, T3 third) {
        return predicate.accept(first, second, third);
    }
}
