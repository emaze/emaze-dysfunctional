package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <R>
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class TransformingBinaryPredicate<R, T1, T2> implements BinaryPredicate<T1, T2> {

    private final Predicate<R> predicate;
    private final BinaryDelegate<R, T1, T2> delegate;

    public TransformingBinaryPredicate(Predicate<R> predicate, BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return predicate.accept(delegate.perform(first, second));
    }
}
