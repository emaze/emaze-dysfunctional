package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Proxies a delegate monitoring its calls.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T> the parameter type
 */
public class MonitoringDelegate<R, T> implements Delegate<R, T> {

    private final Delegate<R, T> nested;
    private final AtomicLong calls;

    public MonitoringDelegate(Delegate<R, T> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R perform(T value) {
        calls.incrementAndGet();
        return nested.perform(value);
    }
}
