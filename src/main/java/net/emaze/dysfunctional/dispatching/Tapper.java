package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Applies an action to an element and returns the (possibly modified) element.
 *
 * @author rferranti
 * @param <T> the parameter type
 */
public class Tapper<T> implements Function<T, T> {

    private final Consumer<T> action;

    public Tapper(Consumer<T> action) {
        dbc.precondition(action != null, "cannot create a Tapper with a null action");
        this.action = action;
    }

    @Override
    public T apply(T t) {
        action.accept(t);
        return t;
    }
}
