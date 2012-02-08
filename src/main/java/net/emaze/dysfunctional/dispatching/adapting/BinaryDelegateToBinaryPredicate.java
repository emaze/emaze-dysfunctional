package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 * Adapts a binary delegate with Boolean result type to a binary Predicate.
 *
 * @param <T1> the adapted delegate first parameter type
 * @param <T2> the adapted delegate second parameter type
 * @author rferranti
 */
public class BinaryDelegateToBinaryPredicate<T1, T2> implements BinaryPredicate<T1, T2> {

    private final BinaryDelegate<Boolean, T1, T2> adapted;

    public BinaryDelegateToBinaryPredicate(BinaryDelegate<Boolean, T1, T2> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public boolean accept(T1 first, T2 second) {
        return adapted.perform(first, second);
    }
}
