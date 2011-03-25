package net.emaze.dysfunctional.dispatching.delegates.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * @param <R> 
 * @param <T1>
 * @param <T2>
 * @author rferranti
 */
public class IgnoreFirst<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final Delegate<R, T2> delegate;

    public IgnoreFirst(Delegate<R, T2> delegate) {
        dbc.precondition(delegate != null, "cannot ignore the first parameter of a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return delegate.perform(second);
    }
}
