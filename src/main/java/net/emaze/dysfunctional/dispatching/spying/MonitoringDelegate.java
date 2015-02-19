package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Proxies a delegate monitoring its calls.
 *
 * @author rferranti
 * @param <T> the parameter type
 * @param <R> the result type
 */
public class MonitoringDelegate<T, R> implements Function<T, R> {

    private final Function<T, R> nested;
    private final AtomicLong calls;

    public MonitoringDelegate(Function<T, R> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R apply(T value) {
        calls.incrementAndGet();
        return nested.apply(value);
    }
}
