package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A stateful predicate yielding true when called the nth time.
 *
 * @param <E> the element type parameter
 * @author rferranti
 */
public class Nth<E> implements Predicate<E> {

    private final long target;
    private long current = 0;

    public Nth(long target) {
        dbc.precondition(target >= 1, "target is 1-based, must be >= 1");
        this.target = target;
    }

    @Override
    public boolean accept(E element) {
        return target == ++current;
    }
}
