package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import java.util.function.Predicate;

/**
 * Composes a predicate with a binary delegate (predicate ° delegate).
 *
 * @param <R> the delegate result type
 * @param <T1> the delegate first parameter type
 * @param <T2> the delegate second parameter type
 * @author rferranti
 */
public class TransformingBinaryPredicate<R, T1, T2> implements BinaryPredicate<T1, T2> {

    private final Predicate<R> predicate;
    private final BiFunction<T1, T2, R> delegate;

    public TransformingBinaryPredicate(Predicate<R> predicate, BiFunction<T1, T2, R> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return predicate.test(delegate.apply(first, second));
    }
}
