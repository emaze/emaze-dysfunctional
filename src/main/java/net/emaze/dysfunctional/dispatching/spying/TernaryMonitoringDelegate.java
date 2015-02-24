package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;

/**
 * Proxies a ternary delegate monitoring its calls.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 */
public class TernaryMonitoringDelegate<T1, T2, T3, R> implements TriFunction<T1, T2, T3, R> {

    private final TriFunction<T1, T2, T3, R> nested;
    private final AtomicLong calls;

    public TernaryMonitoringDelegate(TriFunction<T1, T2, T3, R> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null delegate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public R apply(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        return nested.apply(first, second, third);
    }
}
