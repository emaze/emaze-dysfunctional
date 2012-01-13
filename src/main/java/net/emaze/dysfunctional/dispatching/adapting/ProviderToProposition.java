package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a Provider<Boolean> as a Proposition.
 * @param <T>
 * @author rferranti
 */
public class ProviderToProposition implements Proposition {

    private final Provider<Boolean> adapted;

    public ProviderToProposition(Provider<Boolean> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null provider");
        this.adapted = adapted;
    }

    @Override
    public boolean state() {
        return adapted.provide();
    }
}
