package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 * Proxies a binary delegate monitoring its calls.
 *
 * @author rferranti
 * @param <T1>
 * @param <T2>
 * @param <R>
 */
public class BinaryMonitoringDelegate<T1, T2, R> implements BiFunction<T1, T2, R> {

    private final BiFunction<T1, T2, R> nested;
    private final AtomicLong calls;

    public BinaryMonitoringDelegate(BiFunction<T1, T2, R> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R apply(T1 former, T2 latter) {
        calls.incrementAndGet();
        return nested.apply(former, latter);
    }
}
