package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

/**
 * Composes a predicate with a ternary delegate (predicate ° delegate).
 *
 * @param <R> the delegate result type
 * @param <T1> the delegate first parameter type
 * @param <T2> the delegate second parameter type
 * @param <T3> the delegate third parameter type
 * @author rferranti
 */
public class TransformingTernaryPredicate<R, T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final Predicate<R> predicate;
    private final TernaryDelegate<R, T1, T2, T3> delegate;

    public TransformingTernaryPredicate(Predicate<R> predicate, TernaryDelegate<R, T1, T2, T3> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        return predicate.accept(delegate.perform(first, second, third));
    }
}
