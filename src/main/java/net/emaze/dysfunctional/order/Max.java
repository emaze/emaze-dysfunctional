package net.emaze.dysfunctional.order;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @param <T> 
 * @author rferranti
 */
public class Max<T> implements BinaryOperator<T> {

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
