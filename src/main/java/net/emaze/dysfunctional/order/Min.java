package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class Min<T> implements BinaryDelegate<T, T, T> {

    private final Comparator<T> comparator;

    public Min(Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot create Min with a null comparator");
        this.comparator = comparator;
    }

    @Override
    public T perform(T lhs, T rhs) {
        if (Order.of(comparator.compare(lhs, rhs)).isLte()) {
            return lhs;
        }
        return rhs;
    }
}
