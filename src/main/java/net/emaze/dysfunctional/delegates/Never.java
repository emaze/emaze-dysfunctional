package net.emaze.dysfunctional.delegates;

/**
 * a Null Unary Predicate always returning false ("never" returning true )
 * @param <E> 
 * @author rferranti
 */
public class Never<E> implements Predicate<E>{

    /**
     * yields false
     * @param element the ignored element
     * @return false
     */
    @Override
    public boolean test(E element) {
        return false;
    }

}
