package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.options.Box;

/**
 *
 * @author rferranti
 */
public class CapturingProvider<T> implements Provider<T> {

    private final Provider<T> nested;
    private final Box<T> result;

    public CapturingProvider(Provider<T> nested, Box<T> result) {
        dbc.precondition(nested != null, "cannot capture from a null provider");
        dbc.precondition(result != null, "cannot capture with a null result box");
        this.nested = nested;
        this.result = result;
    }

    @Override
    public T provide() {
        final T provided = nested.provide();
        result.setContent(provided);
        return provided;
    }
}
