package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a delegate capturing parameter and result.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T> the parameter type
 */
public class CapturingDelegate<T, R> implements Function<T, R> {

    private final Function<T, R> nested;
    private final Box<R> result;
    private final Box<T> param;

    public CapturingDelegate(Function<T, R> nested, Box<R> result, Box<T> param) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param != null, "cannot capture with a null param box");
        this.nested = nested;
        this.result = result;
        this.param = param;
    }

    @Override
    public R apply(T value) {
        param.setContent(value);
        final R got = nested.apply(value);
        result.setContent(got);
        return got;
    }
}
