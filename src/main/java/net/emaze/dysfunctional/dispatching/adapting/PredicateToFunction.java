package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a predicate to a function with Boolean result type.
 *
 * @param <T> the adapted predicate parameter type
 * @author rferranti
 */
public class PredicateToFunction<T> implements Function<T, Boolean> {

    private final Predicate<T> adapted;

    public PredicateToFunction(Predicate<T> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null predicate to function");
        this.adapted = adaptee;
    }

    @Override
    public Boolean apply(T value) {
        return adapted.test(value);
    }
}
