package net.emaze.dysfunctional.dispatching.adapting;

import java.util.function.Function;
import java.util.function.Predicate;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Adapts a function with Boolean result type to a predicate.
 *
 * @param <T> the adapted function parameter type
 * @author rferranti
 */
public class FunctionToPredicate<T> implements Predicate<T> {

    private final Function<T, Boolean> adapted;

    public FunctionToPredicate(Function<T, Boolean> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null function to predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T value) {
        return adapted.apply(value);
    }
}
