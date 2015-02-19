package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;

/**
 * Binary to unary action adapter. Adapting is performed by currying the first
 * parameter of the adapted binary action.
 *
 * @param <T1> the adapted action former element Type
 * @param <T2> the adapted action latter element Type
 * @author rferranti
 */
public class ActionBinderFirst<T1, T2> implements Consumer<T2> {

    private final BinaryAction<T1, T2> adapted;
    private final T1 first;

    public ActionBinderFirst(BinaryAction<T1, T2> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind the first parameter of a null binary action");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public void accept(T2 second) {
        adapted.perform(first, second);
    }
}
