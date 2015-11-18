package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Adapts a predicate to a delegate with Boolean result type.
 *
 * @param <T> the adapted predicate parameter type
 * @author rferranti
 */
public class PredicateToDelegate<T> implements Delegate<Boolean, T> {

    private final Predicate<T> adapted;

    public PredicateToDelegate(Predicate<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate to delegate");
        this.adapted = adaptee;
    }

    @Override
    public Boolean perform(T value) {
        return adapted.accept(value);
    }
}