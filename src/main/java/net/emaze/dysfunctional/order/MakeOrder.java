package net.emaze.dysfunctional.order;

import java.util.Comparator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.tuples.Pair;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class MakeOrder<T> implements BiFunction<T, T, Pair<T, T>> {

    private final Comparator<T> comparator;

    public MakeOrder(Comparator<T> comparator) {
        dbc.precondition(comparator != null, "cannot create MakeOrder with a null comparator");
        this.comparator = comparator;
    }

    @Override
    public Pair<T, T> apply(T lhs, T rhs) {
        final Order result = Order.of(comparator.compare(lhs, rhs));
        final T lower = Order.GT == result ? rhs : lhs;
        final T higher = Order.GT == result ? lhs : rhs;
        return Pair.of(lower, higher);
    }
}
