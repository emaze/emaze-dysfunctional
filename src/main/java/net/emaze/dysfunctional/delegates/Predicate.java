package net.emaze.dysfunctional.delegates;

/**
 * A unary functor returning a boolean
 * @param <E> 
 * @author rferranti
 */
public interface Predicate<E> {
    /**
     * tests the predicate against the element
     * @param element the element used to evaluate the predicate
     * @return true if the element fulfills the predicate
     */
    public boolean test(E element);
}
