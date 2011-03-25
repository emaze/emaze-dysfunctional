package net.emaze.dysfunctional.dispatching.logic.adapters;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Binary to unary predicate adapter (curry)
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public class PredicateBinderFirst<T1, T2> implements Predicate<T2> {

    private final BinaryPredicate<T1, T2> predicate;
    private final T1 first;

    public PredicateBinderFirst(BinaryPredicate<T1, T2> predicate, T1 first) {
        dbc.precondition(predicate != null, "cannot bind the first parameter of a null binary predicate");
        this.predicate = predicate;
        this.first = first;
    }

    @Override
    public boolean test(T2 second) {
        return predicate.test(first, second);
    }
}
