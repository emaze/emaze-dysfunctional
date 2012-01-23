package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

public class BinaryMonitoringDelegate<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final BinaryDelegate<R, T1, T2> nested;
    private final AtomicLong calls;

    public BinaryMonitoringDelegate(BinaryDelegate<R, T1, T2> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R perform(T1 former, T2 latter) {
        calls.incrementAndGet();
        return nested.perform(former, latter);
    }
}
