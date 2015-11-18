package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Adapts a unary delegate handling triples to a ternary delegate.
 * @param <R> the result type parameter
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @author rferranti
 */
public class UnaryToTernaryDelegate<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final Delegate<R, Triple<T1, T2, T3>> delegate;

    public UnaryToTernaryDelegate(Delegate<R, Triple<T1, T2, T3>> delegate) {
        dbc.precondition(delegate != null, "cannot create a UnaryToTernaryDelegate with a null Delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        return delegate.perform(Triple.of(first, second, third));
    }
}