package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Adapts a predicate to a delegate with Boolean result type.
 *
 * @param <T> the adapted predicate parameter type
 * @author rferranti
 */
public class PredicateToDelegate<T> implements Function<T, Boolean> {

    private final Predicate<T> adapted;

    public PredicateToDelegate(Predicate<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate to delegate");
        this.adapted = adaptee;
    }

    @Override
    public Boolean apply(T value) {
        return adapted.test(value);
    }
}
