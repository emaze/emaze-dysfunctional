package net.emaze.dysfunctional.dispatching.logic;

/**
 * A binary predicate always returning true.
 *
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @author rferranti
 */
public class BinaryAlways<E1, E2> implements BinaryPredicate<E1, E2> {

    /**
     * Yields true.
     *
     * @param former the ignored element
     * @param latter the ignored element
     * @return true. always.
     */
    @Override
    public boolean accept(E1 former, E2 latter) {
        return true;
    }
}
