package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Adapts a binary predicate to a binary delegate with Boolean result type.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class BinaryPredicateToBinaryDelegate<T1, T2> implements BiFunction<T1, T2, Boolean> {

    private final BiPredicate<T1, T2> adapted;

    public BinaryPredicateToBinaryDelegate(BiPredicate<T1, T2> adaptee) {
        dbc.precondition(adaptee != null, "cannot adapt a null binary predicate to binary delegate");
        this.adapted = adaptee;
    }

    @Override
    public Boolean apply(T1 first, T2 second) {
        return adapted.test(first, second);
    }
}
