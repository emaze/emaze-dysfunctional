package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * @param <R> 
 * @param <T1>
 * @param <T2>
 * @param <T3> 
 * @author rferranti
 */
public class IgnoreThird<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final BinaryDelegate<R, T1, T2> delegate;

    public IgnoreThird(BinaryDelegate<R, T1, T2> delegate) {
        dbc.precondition(delegate != null, "cannot ignore the third parameter of a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        return delegate.perform(first, second);
    }
}
