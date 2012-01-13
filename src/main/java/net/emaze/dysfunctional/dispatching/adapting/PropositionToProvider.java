package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Provider;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a proposition as a Provider<Boolean>.
 * @param <T>
 * @author rferranti
 */
public class PropositionToProvider implements Provider<Boolean> {

    private final Proposition adapted;

    public PropositionToProvider(Proposition adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null proposition");
        this.adapted = adapted;
    }

    @Override
    public Boolean provide() {
        return adapted.state();
    }
}
