package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a proposition as a provider with Boolean result type.
 *
 * @author rferranti
 */
public class PropositionToProvider implements Provider<Boolean> {

    private final Proposition adapted;

    public PropositionToProvider(Proposition adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null proposition to provider");
        this.adapted = adaptee;
    }

    @Override
    public Boolean provide() {
        return adapted.state();
    }
}
