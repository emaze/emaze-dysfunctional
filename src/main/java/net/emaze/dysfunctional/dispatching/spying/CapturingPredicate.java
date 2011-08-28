package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.options.Box;

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
    public boolean accept(T value) {
        param.setContent(value);
        final boolean got = nested.accept(value);
        result.setContent(got);
        return got;
    }
}
