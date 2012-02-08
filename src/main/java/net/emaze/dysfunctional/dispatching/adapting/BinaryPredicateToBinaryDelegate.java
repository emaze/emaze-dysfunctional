package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * Adapts a binary predicate to a binary delegate with Boolean result type.
 *
 * @param <T1> the adapted predicate first parameter type
 * @param <T2> the adapted predicate second parameter type
 * @author rferranti
 */
public class BinaryPredicateToBinaryDelegate<T1, T2> implements BinaryDelegate<Boolean, T1, T2> {

    private final BinaryPredicate<T1, T2> adapted;

    public BinaryPredicateToBinaryDelegate(BinaryPredicate<T1, T2> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null predicate");
        this.adapted = adapted;
    }

    @Override
    public Boolean perform(T1 first, T2 second) {
        return adapted.accept(first, second);
    }
}
