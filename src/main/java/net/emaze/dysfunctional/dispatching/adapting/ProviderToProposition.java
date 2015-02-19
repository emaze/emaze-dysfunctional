package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a provider with Boolean result type to a proposition.
 *
 * @author rferranti
 */
public class ProviderToProposition implements Proposition {

    private final Supplier<Boolean> adapted;

    public ProviderToProposition(Supplier<Boolean> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null provider to proposition");
        this.adapted = adaptee;
    }

    @Override
    public boolean state() {
        return adapted.get();
    }
}
