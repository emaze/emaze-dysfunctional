package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class CapturingDelegate<R, T> implements Delegate<R, T> {

    public final Box<T> first = new Box<T>();
    public final Box<R> result = new Box<R>();
    public final Delegate<R, T> nested;

    public CapturingDelegate(Delegate<R, T> nested) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        this.nested = nested;
    }

    @Override
    public R perform(T value) {
        this.first.setContent(value);
        final R result = nested.perform(value);
        this.result.setContent(result);
        return result;
    }
}
