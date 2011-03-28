package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class TernaryCapturingDelegate<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final Box<T3> third = new Box<T3>();
    public final Box<R> result = new Box<R>();
    public final TernaryDelegate<R, T1, T2, T3> nested;

    public TernaryCapturingDelegate(TernaryDelegate<R, T1, T2, T3> nested) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        this.nested = nested;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        this.first.setContent(first);
        this.second.setContent(second);
        this.third.setContent(third);
        final R result = nested.perform(first, second, third);
        this.result.setContent(result);
        return result;
    }
}
