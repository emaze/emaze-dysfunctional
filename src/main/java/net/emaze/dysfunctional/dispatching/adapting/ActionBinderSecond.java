package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

/**
 * Binary to unary action adapter. Adapting is performed by currying the
 * parameter passed in construction to the adapted action.
 *
 * @param <T1> the adapted action former element type
 * @param <T2> the adapted action latter element type
 * @author rferranti
 */
public class ActionBinderSecond<T1, T2> implements Consumer<T1> {

    private final BiConsumer<T1, T2> adapted;
    private final T2 second;

    public ActionBinderSecond(BiConsumer<T1, T2> adaptee, T2 second) {
        dbc.precondition(adaptee != null, "cannot bind second parameter of a null binary action");
        this.adapted = adaptee;
        this.second = second;
    }

    @Override
    public void accept(T1 first) {
        adapted.accept(first, second);
    }
}
