package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class Max<T> implements BiFunction<T, T, T> {

    private final Comparator<T> comparator;

    public Max(Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot create Max with a null comparator");
        this.comparator = comparator;
    }

    @Override
    public T apply(T lhs, T rhs) {
        if (Order.of(comparator.compare(lhs, rhs)).isGte()) {
            return lhs;
        }
        return rhs;
    }
}
