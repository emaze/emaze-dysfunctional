package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.Predicate;

/**
 * A unary predicate always returning true.
 *
 * @param <E> the parameter type
 * @author rferranti
 */
public class Always<E> implements Predicate<E> {

    /**
     * Yields true.
     *
     * @param element the ignored element
     * @return true. always.
     */
    @Override
    public boolean test(E element) {
        return true;
    }
}
