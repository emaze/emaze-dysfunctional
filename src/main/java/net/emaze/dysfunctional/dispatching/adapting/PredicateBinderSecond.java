package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Binary to unary predicate adapter. Adapting is performed by currying the
 * second parameter of the adapted predicate.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class PredicateBinderSecond<T1, T2> implements Predicate<T1> {

    private final BinaryPredicate<T1, T2> predicate;
    private final T2 second;

    public PredicateBinderSecond(BinaryPredicate<T1, T2> predicate, T2 second) {
        dbc.precondition(predicate != null, "cannot bind the second parameter of a null binary predicate");
        this.predicate = predicate;
        this.second = second;
    }

    @Override
    public boolean accept(T1 first) {
        return predicate.accept(first, second);
    }
}
