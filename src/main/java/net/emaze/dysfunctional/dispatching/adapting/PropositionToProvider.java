package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import java.util.function.BooleanSupplier;

/**
 * Adapts a proposition as a provider with Boolean result type.
 *
 * @author rferranti
 */
public class PropositionToProvider implements Supplier<Boolean> {

    private final BooleanSupplier adapted;

    public PropositionToProvider(BooleanSupplier adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null proposition to provider");
        this.adapted = adaptee;
    }

    @Override
    public Boolean get() {
        return adapted.getAsBoolean();
    }
}
