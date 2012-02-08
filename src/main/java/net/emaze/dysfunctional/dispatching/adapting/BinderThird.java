package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.TernaryDelegate;

/**
 * Ternary to binary delegate adapter. Adapting is performed by currying the
 * third parameter of the adapted ternary delegate.
 *
 * @param <R> the adapted delegate result type
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @param <T3> the adapted delegate third parameter type
 * @author rferranti
 */
public class BinderThird<R, T1, T2, T3> implements BinaryDelegate<R, T1, T2> {

    private final TernaryDelegate<R, T1, T2, T3> adapted;
    private final T3 third;

    public BinderThird(TernaryDelegate<R, T1, T2, T3> adaptee, T3 third) {
        dbc.precondition(adaptee != null, "cannot bind third parameter of a null ternary delegate");
        this.adapted = adaptee;
        this.third = third;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return adapted.perform(first, second, third);
    }
}
