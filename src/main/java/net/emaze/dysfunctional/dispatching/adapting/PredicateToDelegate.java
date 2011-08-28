package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class PredicateToDelegate<T> implements Delegate<Boolean, T> {

    private final Predicate<T> adapted;

    public PredicateToDelegate(Predicate<T> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null predicate");
        this.adapted = adapted;
    }

    @Override
    public Boolean perform(T value) {
        return adapted.accept(value);
    }
}
