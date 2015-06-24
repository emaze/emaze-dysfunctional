package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BooleanSupplier;

/**
 * Proxies a proposition monitoring its calls.
 *
 * @author rferranti
 */
public class MonitoringProposition implements BooleanSupplier {

    private final BooleanSupplier nested;
    private final AtomicLong calls;

    public MonitoringProposition(BooleanSupplier nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null proposition");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public boolean getAsBoolean() {
        calls.incrementAndGet();
        return nested.getAsBoolean();
    }
}
