package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Ternary to binary predicate adapter (curry)
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element type
 * @author rferranti
 */
public class PredicateBinderFirstOfThree<T1, T2, T3> implements BinaryPredicate<T2, T3> {

    private final TernaryPredicate<T1, T2, T3> predicate;
    private final T1 first;

    public PredicateBinderFirstOfThree(TernaryPredicate<T1, T2, T3> predicate, T1 first) {
        dbc.precondition(predicate != null, "cannot bind the first parameter of a null ternary predicate");
        this.predicate = predicate;
        this.first = first;
    }

    @Override
    public boolean accept(T2 second, T3 third) {
        return predicate.accept(first, second, third);
    }
}
