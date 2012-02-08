package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a delegate capturing parameter and result.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T> the parameter type
 */
public class CapturingDelegate<R, T> implements Delegate<R, T> {

    private final Delegate<R, T> nested;
    private final Box<R> result;
    private final Box<T> param;

    public CapturingDelegate(Delegate<R, T> nested, Box<R> result, Box<T> param) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param != null, "cannot capture with a null param box");
        this.nested = nested;
        this.result = result;
        this.param = param;
    }

    @Override
    public R perform(T value) {
        param.setContent(value);
        final R got = nested.perform(value);
        result.setContent(got);
        return got;
    }
}
