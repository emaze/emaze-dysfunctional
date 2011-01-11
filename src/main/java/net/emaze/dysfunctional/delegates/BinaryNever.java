package net.emaze.dysfunctional.delegates;

/**
 * a Null Binary Predicate always returning false ("never" returning true )
 * @param <E1> the former type parameter
 * @param <E2> the latter type parameter
 * @author rferranti
 */
public class BinaryNever<E1, E2> implements BinaryPredicate<E1, E2>{

    /**
     * yields false.
     * @param former the former ignored element
     * @param latter the latter ignored element
     * @return false
     */
    @Override
    public boolean test(E1 former, E2 latter) {
        return false;
    }

}
