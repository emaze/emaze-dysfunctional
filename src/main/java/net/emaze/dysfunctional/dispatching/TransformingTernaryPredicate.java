package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Composes a predicate with a ternary delegate (predicate ° delegate).
 *
 * @param <R> the delegate result type
 * @param <T1> the delegate first parameter type
 * @param <T2> the delegate second parameter type
 * @param <T3> the delegate third parameter type
 * @author rferranti
 */
public class TransformingTernaryPredicate<T1, T2, T3, R> implements TriPredicate<T1, T2, T3> {

    private final Predicate<R> predicate;
    private final TriFunction<T1, T2, T3, R> delegate;

    public TransformingTernaryPredicate(Predicate<R> predicate, TriFunction<T1, T2, T3, R> delegate) {
        dbc.precondition(predicate != null, "cannot compose delegate with a null predicate");
        dbc.precondition(delegate != null, "cannot compose predicate with a null delegate");
        this.predicate = predicate;
        this.delegate = delegate;
    }

    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        return predicate.test(delegate.apply(first, second, third));
    }
}
