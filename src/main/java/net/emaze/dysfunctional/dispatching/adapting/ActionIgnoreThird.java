package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Adapts a binary action to a ternary action. Adapting is performed by ignoring
 * the third parameter passed to the adapted action.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <T3> the adapter third parameter type
 * @author rferranti
 */
public class ActionIgnoreThird<T1, T2, T3> implements TernaryAction<T1, T2, T3> {

    private final BiConsumer<T1, T2> adapted;

    public ActionIgnoreThird(BiConsumer<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore third parameter of a null action");
        this.adapted = adaptee;
    }

    @Override
    public void perform(T1 first, T2 second, T3 third) {
        adapted.accept(first, second);
    }
}
