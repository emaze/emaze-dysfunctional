package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a supplier as a runnable. Adapting is performed by ignoring result of
 * the adapted supplier.
 *
 * @author rferranti
 */
public class SupplierToRunnable implements Runnable {

    private final Supplier<?> adapted;

    public SupplierToRunnable(Supplier<?> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null supplier to runnable");
        this.adapted = adaptee;
    }

    @Override
    public void run() {
        adapted.get();
    }
}
