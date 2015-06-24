package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;

/**
 * Proxies a ternary consumer monitoring its calls.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 */
public class TernaryMonitoringConsumer<T1, T2, T3> implements TriConsumer<T1, T2, T3> {

    private final TriConsumer<T1, T2, T3> nested;
    private final AtomicLong calls;

    public TernaryMonitoringConsumer(TriConsumer<T1, T2, T3> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null consumer");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void accept(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        nested.accept(first, second, third);
    }
}
