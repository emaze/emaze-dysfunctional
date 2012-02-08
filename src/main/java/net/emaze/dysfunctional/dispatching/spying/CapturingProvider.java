package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a provider capturing result.
 *
 * @author rferranti
 * @param <R> the result type
 */
public class CapturingProvider<R> implements Provider<R> {

    private final Provider<R> nested;
    private final Box<R> result;

    public CapturingProvider(Provider<R> nested, Box<R> result) {
        dbc.precondition(nested != null, "cannot capture from a null provider");
        dbc.precondition(result != null, "cannot capture with a null result box");
        this.nested = nested;
        this.result = result;
    }

    @Override
    public R provide() {
        final R provided = nested.provide();
        result.setContent(provided);
        return provided;
    }
}
