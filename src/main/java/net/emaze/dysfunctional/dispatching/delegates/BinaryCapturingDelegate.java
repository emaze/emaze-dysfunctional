package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

public class BinaryCapturingDelegate<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    public final Box<T1> first = new Box<T1>();
    public final Box<T2> second = new Box<T2>();
    public final Box<R> result = new Box<R>();
    public final BinaryDelegate<R, T1, T2> nested;

    public BinaryCapturingDelegate(BinaryDelegate<R, T1, T2> nested) {
        dbc.precondition(nested != null, "cannot capture from a null delegate");
        this.nested = nested;
    }

    @Override
    public R perform(T1 former, T2 latter) {
        this.first.setContent(former);
        this.second.setContent(latter);
        final R got = nested.perform(former, latter);
        this.result.setContent(got);
        return got;
    }
}
