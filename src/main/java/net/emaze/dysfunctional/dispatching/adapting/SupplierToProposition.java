package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a supplier with Boolean result type to a proposition.
 *
 * @author rferranti
 */
public class SupplierToProposition implements BooleanSupplier {

    private final Supplier<Boolean> adapted;

    public SupplierToProposition(Supplier<Boolean> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null supplier to proposition");
        this.adapted = adaptee;
    }

    @Override
    public boolean getAsBoolean() {
        return adapted.get();
    }
}
