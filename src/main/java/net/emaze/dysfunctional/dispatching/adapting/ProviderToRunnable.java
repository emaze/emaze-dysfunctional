package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Adapts a provider as a runnable. Adapting is performed by ignoring result of
 * the adapted provider.
 *
 * @author rferranti
 */
public class ProviderToRunnable implements Runnable {

    private final Provider<?> adapted;

    public ProviderToRunnable(Provider<?> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null provider");
        this.adapted = adapted;
    }

    @Override
    public void run() {
        adapted.provide();
    }
}
