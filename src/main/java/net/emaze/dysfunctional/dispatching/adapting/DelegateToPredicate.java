package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Adapts a delegate with Boolean result type to a predicate.
 *
 * @param <T> the adapted delegate parameter type
 * @author rferranti
 */
public class DelegateToPredicate<T> implements Predicate<T> {

    private final Delegate<Boolean, T> adapted;

    public DelegateToPredicate(Delegate<Boolean, T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null delegate to predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean accept(T value) {
        return adapted.perform(value);
    }
}
