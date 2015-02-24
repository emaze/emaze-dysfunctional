package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a ternary function capturing parameters and result.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 * @param <R> the result type
 */
public class TernaryCapturingFunction<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final TriFunction<T1, T2, T3, R> nested;
    private final Box<R> result;
    private final Box<T1> param1;
    private final Box<T2> param2;
    private final Box<T3> param3;

    public TernaryCapturingFunction(TriFunction<T1, T2, T3, R> nested, Box<R> result, Box<T1> param1, Box<T2> param2, Box<T3> param3) {
        dbc.precondition(nested != null, "cannot capture from a null function");
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
    public R apply(T1 first, T2 second, T3 third) {
        param1.setContent(first);
        param2.setContent(second);
        param3.setContent(third);
        final R got = nested.apply(first, second, third);
        result.setContent(got);
        return got;
    }
}
