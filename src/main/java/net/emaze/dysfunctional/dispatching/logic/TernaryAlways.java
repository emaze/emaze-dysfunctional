package net.emaze.dysfunctional.dispatching.logic;

/**
 * a Null Ternary Predicate always returning true
 * @param <E1> the first type parameter
 * @param <E2> the second type parameter
 * @param <E3> the third type parameter
 * @author rferranti
 */
public class TernaryAlways<E1, E2, E3> implements TernaryPredicate<E1, E2, E3>{

    /**
     * yields true.
     * @param first the first ignored element
     * @param second the second ignored element
     * @param third the third ignored element
     * @return true. always.
     */
    @Override
    public boolean accept(E1 first, E2 second, E3 third) {
        return true;
    }

}
