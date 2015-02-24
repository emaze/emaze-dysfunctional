package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Proxies a supplier monitoring its calls.
 *
 * @author rferranti
 * @param <R> the result type
 */
public class MonitoringSupplier<R> implements Supplier<R> {

    private final Supplier<R> nested;
    private final AtomicLong calls;

    public MonitoringSupplier(Supplier<R> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null supplier");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R get() {
        calls.incrementAndGet();
        return nested.get();
    }
}
