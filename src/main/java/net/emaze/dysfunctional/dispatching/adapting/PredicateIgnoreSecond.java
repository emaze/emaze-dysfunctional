package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Unary to binary predicate adapter. Adapting is performed by ignoring the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
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
