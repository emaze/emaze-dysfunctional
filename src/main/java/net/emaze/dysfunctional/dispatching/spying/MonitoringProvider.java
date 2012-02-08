package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Proxies a provider monitoring its calls.
 *
 * @author rferranti
 * @param <R> the result type
 */
public class MonitoringProvider<R> implements Provider<R> {

    private final Provider<R> nested;
    private final AtomicLong calls;

    public MonitoringProvider(Provider<R> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null provider");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R provide() {
        calls.incrementAndGet();
        return nested.provide();
    }
}
