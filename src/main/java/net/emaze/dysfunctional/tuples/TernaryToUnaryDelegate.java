package net.emaze.dysfunctional.tuples;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Adapts a ternary delegate handling triples to a delegate handling triples.
 * @param <T1> the first type parameter
 * @param <T2> the second type parameter
 * @param <T3> the third type parameter
 * @param <R> the result type parameter
 * @author rferranti
 */
public class TernaryToUnaryDelegate<T1, T2, T3, R> implements Function<Triple<T1, T2, T3>, R> {

    private final TriFunction<T1, T2, T3, R> delegate;

    public TernaryToUnaryDelegate(TriFunction<T1, T2, T3, R> delegate) {
        dbc.precondition(delegate != null, "cannot create a TernaryToUnaryDelegate with a null TernaryDelegate");
        this.delegate = delegate;
    }

    @Override
    public R apply(Triple<T1, T2, T3> triple) {
        return delegate.apply(triple.first(), triple.second(), triple.third());
    }
}
