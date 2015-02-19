package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;

/**
 * Proxies a binary action monitoring its calls.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryMonitoringAction<T1, T2> implements BiConsumer<T1, T2> {

    private final BiConsumer<T1, T2> nested;
    private final AtomicLong calls;

    public BinaryMonitoringAction(BiConsumer<T1, T2> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null action");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void accept(T1 former, T2 latter) {
        calls.incrementAndGet();
        nested.accept(former, latter);
    }
}
