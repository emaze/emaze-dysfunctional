package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.options.Box;

public class BinaryCapturingDelegate<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final BinaryDelegate<R, T1, T2> nested;
    private final Box<R> result;
    private final Box<T1> param1;
    private final Box<T2> param2;

    public BinaryCapturingDelegate(BinaryDelegate<R, T1, T2> nested, Box<R> result, Box<T1> param1, Box<T2> param2) {
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
    public R perform(T1 former, T2 latter) {
        param1.setContent(former);
        param2.setContent(latter);
        final R got = nested.perform(former, latter);
        result.setContent(got);
        return got;
    }
}
