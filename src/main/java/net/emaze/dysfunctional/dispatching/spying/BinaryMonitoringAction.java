package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Proxies a binary action monitoring its calls.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryMonitoringAction<T1, T2> implements BinaryAction<T1, T2> {

    private final BinaryAction<T1, T2> nested;
    private final AtomicLong calls;

    public BinaryMonitoringAction(BinaryAction<T1, T2> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null action");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void perform(T1 former, T2 latter) {
        calls.incrementAndGet();
        nested.perform(former, latter);
    }
}
