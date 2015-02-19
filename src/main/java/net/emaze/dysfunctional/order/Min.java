package net.emaze.dysfunctional.order;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class Min<T> implements BinaryOperator<T> {

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
