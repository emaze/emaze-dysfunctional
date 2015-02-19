package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Adapts an action to a binary action. Adapting is performed by ignoring the
 * second parameter passed to the adapted action.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @author rferranti
 */
public class ActionIgnoreSecond<T1, T2> implements BinaryAction<T1, T2> {

    private final Consumer<T1> adapted;

    public ActionIgnoreSecond(Consumer<T1> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second) {
        adapted.accept(first);
    }
}
