package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Adapts a binary function with Boolean result type to a binary Predicate.
 *
 * @param <T1> the adapted function first parameter type
 * @param <T2> the adapted function second parameter type
 * @author rferranti
 */
public class BinaryFunctionToPredicate<T1, T2> implements BiPredicate<T1, T2> {

    private final BiFunction<T1, T2, Boolean> adapted;

    public BinaryFunctionToPredicate(BiFunction<T1, T2, Boolean> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary function to binary predicate");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T1 first, T2 second) {
        return adapted.apply(first, second);
    }
}
