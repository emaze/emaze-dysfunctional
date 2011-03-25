package net.emaze.dysfunctional.dispatching.logic.adapters;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class PredicateIgnoreFirst<T1, T2> implements BinaryPredicate<T1, T2> {

    private final Predicate<T2> predicate;

    public PredicateIgnoreFirst(Predicate<T2> predicate) {
        dbc.precondition(predicate != null, "cannot ignore the first parameter with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return predicate.accept(second);
    }
}
