package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Supplier;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a runnable to a supplier. Adapter result type is Void and always
 * yields null.
 *
 * @author rferranti
 */
public class RunnableToSupplier implements Supplier<Void> {

    private final Runnable adapted;

    public RunnableToSupplier(Runnable adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null runnable to supplier");
        this.adapted = adaptee;
    }

    @Override
    public Void get() {
        adapted.run();
        return null;
    }
}
