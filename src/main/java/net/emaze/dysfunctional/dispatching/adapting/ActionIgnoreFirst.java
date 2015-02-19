package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 * Adapts an action to a binary action. Adapting is performed by ignoring the
 * first parameter passed to the adapted action.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @author rferranti
 */
public class ActionIgnoreFirst<T1, T2> implements BiConsumer<T1, T2> {

    private final Consumer<T2> adapted;

    public ActionIgnoreFirst(Consumer<T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore first parameter of a null action");
        this.adapted = adaptee;
    }

    @Override
    public void accept(T1 first, T2 second) {
        adapted.accept(second);
    }
}
