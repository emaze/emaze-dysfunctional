package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;

/**
 * Adapts a Runnable as an Action<T> ignoring the passed parameter.
 * @param <T>
 * @author rferranti
 */
public class RunnableIgnoreParameter<T> implements Action<T> {

    private final Runnable adapted;

    public RunnableIgnoreParameter(Runnable adapted) {
        dbc.precondition(adapted != null, "cannot ignore parameter with a null Runnable");
        this.adapted = adapted;
    }

    @Override
    public void perform(T parameter) {
        adapted.run();
    }
}
