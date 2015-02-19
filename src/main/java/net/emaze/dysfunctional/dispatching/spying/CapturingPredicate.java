package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a predicate capturing parameter and result.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class CapturingPredicate<T> implements Predicate<T> {

    private final Predicate<T> nested;
    private final Box<Boolean> result;
    private final Box<T> param;

    public CapturingPredicate(Predicate<T> nested, Box<Boolean> result, Box<T> param) {
        dbc.precondition(nested != null, "cannot capture from a null predicate");
        dbc.precondition(result != null, "cannot capture with a null result box");
        dbc.precondition(param != null, "cannot capture with a null param box");
        this.nested = nested;
        this.result = result;
        this.param = param;
    }

    @Override
    public boolean test(T value) {
        param.setContent(value);
        final boolean got = nested.test(value);
        result.setContent(got);
        return got;
    }
}
