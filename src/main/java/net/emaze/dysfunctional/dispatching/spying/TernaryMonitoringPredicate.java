package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;

/**
 * Proxies a ternary function, monitoring its calls.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 */
public class TernaryMonitoringPredicate<T1, T2, T3> implements TriPredicate<T1, T2, T3> {

    private final TriPredicate<T1, T2, T3> nested;
    private final AtomicLong calls;

    public TernaryMonitoringPredicate(TriPredicate<T1, T2, T3> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null predicate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean test(T1 first, T2 second, T3 third) {
        calls.incrementAndGet();
        return nested.test(first, second, third);
    }
}
