package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;

/**
 * Adapts a runnable to a provider. Adapter result type is Void and always
 * yields null.
 *
 * @author rferranti
 */
public class RunnableToProvider implements Supplier<Void> {

    private final Runnable adapted;

    public RunnableToProvider(Runnable adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null runnable to provider");
        this.adapted = adaptee;
    }

    @Override
    public Void get() {
        adapted.run();
        return null;
    }
}
