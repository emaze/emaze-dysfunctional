package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 * Adapts a runnable as an action. Adapting is performed by ignoring parameter
 * of the adapted action.
 *
 * @param <T> the adapter parameter type
 * @author rferranti
 */
public class RunnableIgnoreParameter<T> implements Action<T> {

    private final Runnable adapted;

    public RunnableIgnoreParameter(Runnable adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore parameter of a null runnable");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T parameter) {
        adapted.run();
    }
}
