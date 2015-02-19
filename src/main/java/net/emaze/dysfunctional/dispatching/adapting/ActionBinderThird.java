package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryAction;

/**
 * Ternary to binary action adapter. Adapting is performed by currying the third
 * parameter of the adapted ternary action.
 *
 * @param <T1> the adapted action first element Type
 * @param <T2> the adapted action second element Type
 * @param <T3> the adapted action third element type
 * @author rferranti
 */
public class ActionBinderThird<T1, T2, T3> implements BiConsumer<T1, T2> {

    private final TernaryAction<T1, T2, T3> adapted;
    private final T3 third;

    public ActionBinderThird(TernaryAction<T1, T2, T3> adaptee, T3 third) {
        dbc.precondition(adaptee != null, "cannot bind third parameter of a null ternary action");
        this.adapted = adaptee;
        this.third = third;
    }

    @Override
    public void accept(T1 first, T2 second) {
        adapted.perform(first, second, third);
    }
}
