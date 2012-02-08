package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * Proxies a binary predicate monitoring its calls.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryMonitoringPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final BinaryPredicate<T1, T2> nested;
    private final AtomicLong calls;

    public BinaryMonitoringPredicate(BinaryPredicate<T1, T2> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null predicate");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean accept(T1 former, T2 latter) {
        calls.incrementAndGet();
        return nested.accept(former, latter);
    }
}
