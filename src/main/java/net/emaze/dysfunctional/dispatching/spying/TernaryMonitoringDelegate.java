package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

public class TernaryMonitoringDelegate<R, T1, T2, T3> implements TernaryDelegate<R, T1, T2, T3> {

    private final TernaryDelegate<R, T1, T2, T3> nested;
    private final AtomicLong calls;

    public TernaryMonitoringDelegate(TernaryDelegate<R, T1, T2, T3> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R perform(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        return nested.perform(first, second, third);
    }
}
