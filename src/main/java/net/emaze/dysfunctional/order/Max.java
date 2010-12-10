package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.BinaryDelegate;

/**
 *
 * @author rferranti
 */
public class Max<T> implements BinaryDelegate<T, T, T> {

    private final Comparator<T> comparator;

    public Max(Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot create Max with a null comparator");
        this.comparator = comparator;
    }

    @Override
    public T perform(T lhs, T rhs) {
        final int result = comparator.compare(lhs, rhs);
        if (result == Order.LHS_IS_GREATER || result == Order.SAME_ORDER) {
            return lhs;
        }
        return rhs;        
    }
}
