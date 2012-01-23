package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

public class MonitoringAction<T> implements Action<T> {

    private final Action<T> nested;
    private final AtomicLong calls;

    public MonitoringAction(Action<T> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null action");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void perform(T value) {
        calls.incrementAndGet();
        nested.perform(value);
    }
}
