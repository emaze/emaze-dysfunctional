package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a provider capturing result.
 *
 * @author rferranti
 * @param <R> the result type
 */
public class CapturingProvider<R> implements Supplier<R> {

    private final Supplier<R> nested;
    private final Box<R> result;

    public CapturingProvider(Supplier<R> nested, Box<R> result) {
        dbc.precondition(nested != null, "cannot capture from a null provider");
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
