package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Proxies a proposition monitoring its calls.
 *
 * @author rferranti
 */
public class MonitoringProposition implements Proposition {

    private final Proposition nested;
    private final AtomicLong calls;

    public MonitoringProposition(Proposition nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null proposition");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean state() {
        calls.incrementAndGet();
        return nested.state();
    }
}
