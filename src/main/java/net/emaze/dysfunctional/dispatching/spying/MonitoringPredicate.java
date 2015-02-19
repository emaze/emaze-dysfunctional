package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * Proxies a predicate monitoring its calls.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class MonitoringPredicate<T> implements Predicate<T> {

    private final Predicate<T> nested;
    private final AtomicLong calls;

    public MonitoringPredicate(Predicate<T> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null predicate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean test(T value) {
        calls.incrementAndGet();
        return nested.test(value);
    }
}
