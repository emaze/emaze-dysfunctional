package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import java.util.function.Function;

/**
 * Adapts an action to a delegate. Adapter result type is Void and always yields
 * null.
 *
 * @param <T> the adapted action parameter type
 * @author rferranti
 */
public class ActionToDelegate<T> implements Function<T, Void> {

    private final Action<T> adapted;

    public ActionToDelegate(Action<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null action to a delegate");
        this.adapted = adaptee;
    }

    @Override
    public Void apply(T value) {
        adapted.perform(value);
        return null;
    }
}
