package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.Action;
import java.util.function.Function;

/**
 * Applies an action to an element and returns the (possibly modified) element.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class Tapper<T> implements Function<T, T> {

    private final Action<T> action;

    public Tapper(Action<T> action) {
        dbc.precondition(action != null, "cannot create a Tapper with a null action");
        this.action = action;
    }

    @Override
    public T apply(T t) {
        action.perform(t);
        return t;
    }
}
