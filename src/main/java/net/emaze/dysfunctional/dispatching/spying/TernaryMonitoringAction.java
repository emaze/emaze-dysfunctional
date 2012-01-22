package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

public class TernaryMonitoringAction<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final TernaryAction<T1, T2, T3> nested;
    private final AtomicLong calls;

    public TernaryMonitoringAction(TernaryAction<T1, T2, T3> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null action");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        nested.perform(first, second, third);
    }
}
