package net.emaze.dysfunctional.dispatching.logic;

/**
 * A ternary predicate always returning false. ("never" returning true ).
 *
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @param <E3> the third type parameter
 * @author rferranti
 */
public class TernaryNever<E1, E2, E3> implements TernaryPredicate<E1, E2, E3> {

    /**
     * Yields false.
     *
     * @param first the first ignored element
     * @param second the second ignored element
     * @param third the third ignored element
     * @return false
     */
    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        return false;
    }
}
