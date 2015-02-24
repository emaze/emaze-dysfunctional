package net.emaze.dysfunctional.dispatching.spying;

import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a supplier capturing result.
 *
 * @author rferranti
 * @param <R> the result type
 */
public class CapturingSupplier<R> implements Supplier<R> {

    private final Supplier<R> nested;
    private final Box<R> result;

    public CapturingSupplier(Supplier<R> nested, Box<R> result) {
        dbc.precondition(nested != null, "cannot capture from a null supplier");
        dbc.precondition(result != null, "cannot capture with a null result box");
        this.nested = nested;
        this.result = result;
    }

    @Override
    public R get() {
        final R provided = nested.get();
        result.setContent(provided);
        return provided;
    }
}
