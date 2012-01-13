package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Adapts a Runnable as a Provider<Void>.
 *
 * @param <T>
 * @author rferranti
 */
public class RunnableToProvider implements Provider<Void> {

    private final Runnable adapted;

    public RunnableToProvider(Runnable adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null runnable");
        this.adapted = adapted;
    }

    @Override
    public Void provide() {
        adapted.run();
        return null;
    }
}
