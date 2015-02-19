package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter. Adapting is performed by currying the first
 * parameter of the adapted ternary action.
 *
 * @param <T1> the adapted action first element Type
 * @param <T2> the adapted action second element Type
 * @param <T3> the adapted action third element type
 * @author rferranti
 */
public class ActionBinderFirstOfThree<T1, T2, T3> implements BiConsumer<T2, T3> {

    private final TernaryAction<T1, T2, T3> adapted;
    private final T1 first;

    public ActionBinderFirstOfThree(TernaryAction<T1, T2, T3> adaptee, T1 first) {
        dbc.precondition(adaptee != null, "cannot bind first parameter of a null ternary action");
        this.adapted = adaptee;
        this.first = first;
    }

    @Override
    public void accept(T2 second, T3 third) {
        adapted.perform(first, second, third);
    }
}
