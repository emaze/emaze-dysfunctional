package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Composes a predicate with a delegate (predicate ° delegate).
 *
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 * @author rferranti
 */
public class TransformingPredicate<R, T> implements Predicate<T> {

    private final Predicate<R> predicate;
    private final Function<T, R> delegate;

    public TransformingPredicate(Predicate<R> predicate, Function<T, R> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean test(T element) {
        return predicate.test(delegate.apply(element));
    }
}
