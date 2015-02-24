package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a proposition as a supplier with Boolean result type.
 *
 * @author rferranti
 */
public class PropositionToSupplier implements Supplier<Boolean> {

    private final BooleanSupplier adapted;

    public PropositionToSupplier(BooleanSupplier adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null proposition to supplier");
        this.adapted = adaptee;
    }

    @Override
    public Boolean get() {
        return adapted.getAsBoolean();
    }
}
