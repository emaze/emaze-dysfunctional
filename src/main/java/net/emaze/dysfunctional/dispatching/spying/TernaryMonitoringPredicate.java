package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TernaryPredicate;

public class TernaryMonitoringPredicate<T1, T2, T3> implements TernaryPredicate<T1, T2, T3> {

    private final TernaryPredicate<T1, T2, T3> nested;
    private final AtomicLong calls;

    public TernaryMonitoringPredicate(TernaryPredicate<T1, T2, T3> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null predicate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean accept(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        return nested.accept(first, second, third);
    }
}
