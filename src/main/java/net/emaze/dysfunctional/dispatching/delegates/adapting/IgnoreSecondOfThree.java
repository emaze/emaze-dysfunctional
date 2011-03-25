package net.emaze.dysfunctional.dispatching.delegates.adapting;

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
public class IgnoreSecondOfThree<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final BinaryDelegate<R, T1, T3> delegate;

    public IgnoreSecondOfThree(BinaryDelegate<R, T1, T3> delegate) {
        dbc.precondition(delegate != null, "cannot ignore the second parameter of a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        return delegate.perform(first, third);
    }
}
