package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a binary delegate capturing parameters and result.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <R> the result type
 */
public class BinaryCapturingDelegate<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final BiFunction<T1, T2, R> nested;
    private final Box<R> result;
    private final Box<T1> param1;
    private final Box<T2> param2;

    public BinaryCapturingDelegate(BiFunction<T1, T2, R> nested, Box<R> result, Box<T1> param1, Box<T2> param2) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture from a null param2 box");
        this.nested = nested;
        this.result = result;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public R apply(T1 former, T2 latter) {
        param1.setContent(former);
        param2.setContent(latter);
        final R got = nested.apply(former, latter);
        result.setContent(got);
        return got;
    }
}
