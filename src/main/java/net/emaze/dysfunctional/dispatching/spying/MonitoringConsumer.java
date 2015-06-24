package net.emaze.dysfunctional.dispatching.spying;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Proxies an consumer monitoring its calls.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class MonitoringConsumer<T> implements Consumer<T> {

    private final Consumer<T> nested;
    private final AtomicLong calls;

    public MonitoringConsumer(Consumer<T> nested, AtomicLong calls) {
        dbc.precondition(nested != null, "cannot monitor a null consumer");
        dbc.precondition(calls != null, "cannot monitor with a null AtomicLong");
        this.nested = nested;
        this.calls = calls;
    }

    @Override
    public void accept(T value) {
        calls.incrementAndGet();
        nested.accept(value);
    }
}
