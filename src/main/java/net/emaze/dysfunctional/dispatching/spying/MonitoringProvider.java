package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 *
 * @author rferranti
 */
public class MonitoringProvider<T> implements Provider<T> {

    private final Provider<T> nested;
    private final AtomicLong calls;

    public MonitoringProvider(Provider<T> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null provider");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public T provide() {
        calls.incrementAndGet();
        return nested.provide();
    }
}
