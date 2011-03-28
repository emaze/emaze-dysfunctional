package net.emaze.dysfunctional.dispatching.logic.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class PredicateIgnoreSecond<T1, T2> implements BinaryPredicate<T1, T2> {

    private final Predicate<T1> predicate;

    public PredicateIgnoreSecond(Predicate<T1> predicate) {
        dbc.precondition(predicate != null, "cannot ignore the second parameter with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return predicate.accept(first);
    }
}
