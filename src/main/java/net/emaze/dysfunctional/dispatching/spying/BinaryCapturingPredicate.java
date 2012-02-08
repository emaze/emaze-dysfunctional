package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a binary predicate capturing parameters and result.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryCapturingPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final BinaryPredicate<T1, T2> nested;
    private final Box<Boolean> result;
    private final Box<T1> param1;
    private final Box<T2> param2;

    public BinaryCapturingPredicate(BinaryPredicate<T1, T2> nested, Box<Boolean> result, Box<T1> param1, Box<T2> param2) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture with a null param2 box");
        this.nested = nested;
        this.result = result;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public boolean accept(T1 former, T2 latter) {
        param1.setContent(former);
        param2.setContent(latter);
        final boolean got = nested.accept(former, latter);
        result.setContent(got);
        return got;
    }
}
