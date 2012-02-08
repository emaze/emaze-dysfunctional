package net.emaze.dysfunctional.dispatching.logic;

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
    public boolean accept(E element) {
        return true;
    }
}
