package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Binary to unary predicate adapter. Adapting is performed by currying the
 * first parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second element type
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
    public boolean accept(T2 second) {
        return predicate.accept(first, second);
    }
}
