package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.logic.BinaryPredicate;

/**
 *
 * @param <T1>
 * @param <T2> 
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
