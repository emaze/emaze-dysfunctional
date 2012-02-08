package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Composes a predicate with a delegate (predicate ° delegate).
 *
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 * @author rferranti
 */
public class TransformingPredicate<R, T> implements Predicate<T> {

    private final Predicate<R> predicate;
    private final Delegate<R, T> delegate;

    public TransformingPredicate(Predicate<R> predicate, Delegate<R, T> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean accept(T element) {
        return predicate.accept(delegate.perform(element));
    }
}
