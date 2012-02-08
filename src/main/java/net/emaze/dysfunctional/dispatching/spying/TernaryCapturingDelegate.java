package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a ternary delegate capturing parameters and result.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 */
public class TernaryCapturingDelegate<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final TernaryDelegate<R, T1, T2, T3> nested;
    private final Box<R> result;
    private final Box<T1> param1;
    private final Box<T2> param2;
    private final Box<T3> param3;

    public TernaryCapturingDelegate(TernaryDelegate<R, T1, T2, T3> nested, Box<R> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture from a null param2 box");
        dbc.precondition(param3 != null, "cannot capture from a null param3 box");
        this.nested = nested;
        this.result = result;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        param1.setContent(first);
        param2.setContent(second);
        param3.setContent(third);
        final R got = nested.perform(first, second, third);
        result.setContent(got);
        return got;
    }
}
