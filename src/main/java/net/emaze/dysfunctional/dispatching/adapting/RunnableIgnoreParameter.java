package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;

/**
 * Adapts a runnable as an action. Adapting is performed by ignoring parameter
 * of the adapted action.
 *
 * @param <T> the adapter parameter type
 * @author rferranti
 */
public class RunnableIgnoreParameter<T> implements Consumer<T> {

    private final Runnable adapted;

    public RunnableIgnoreParameter(Runnable adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore parameter of a null runnable");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T parameter) {
        adapted.run();
    }
}
