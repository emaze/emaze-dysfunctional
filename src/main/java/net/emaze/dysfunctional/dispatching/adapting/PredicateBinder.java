package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * Unary to nullary predicate adapter. Adapting is performed by currying the
 * parameter of the adapted predicate.
 *
 * @param <T> the adapted predicate parameter type
 * @author rferranti
 */
public class PredicateBinder<T> implements Proposition {

    private final Predicate<T> adapted;
    private final T parameter;

    public PredicateBinder(Predicate<T> adaptee, T parameter) {
        dbc.precondition(adaptee != null, "cannot bind parameter of a null predicate");
        this.adapted = adaptee;
        this.parameter = parameter;
    }

    @Override
    public boolean state() {
        return adapted.accept(parameter);
    }
}
