package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.BinaryDelegate;

/**
 *
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
        final int result = comparator.compare(lhs, rhs);
        if (result == Order.LHS_IS_LESSER || result == Order.SAME_ORDER) {
            return lhs;
        }
        return rhs;
    }
}
