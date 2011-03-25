package net.emaze.dysfunctional.dispatching.logic;

/**
 * a Null Unary Predicate always returning false ("never" returning true ).
 * @param <E> the type parameter
 * @author rferranti
 */
public class Never<E> implements Predicate<E>{

    /**
     * yields false.
     * @param element the ignored element
     * @return false
     */
    @Override
    public boolean accept(E element) {
        return false;
    }

}
