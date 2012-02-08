package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Proxies a runnable monitoring its calls.
 *
 * @author rferranti
 */
public class MonitoringRunnable implements Runnable {

    private final Runnable nested;
    private final AtomicLong calls;

    public MonitoringRunnable(Runnable nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null runnable");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void run() {
        calls.incrementAndGet();
        nested.run();
    }
}
