package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class Min<T> implements BiFunction<T, T, T> {

    private final Comparator<T> comparator;

    public Min(Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot create Min with a null comparator");
        this.comparator = comparator;
    }

    @Override
    public T apply(T lhs, T rhs) {
        if (Order.of(comparator.compare(lhs, rhs)).isLte()) {
            return lhs;
        }
        return rhs;
    }
}
