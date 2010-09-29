package net.emaze.dysfunctional.delegates;

/**
 * A unary functor returning a boolean
 * @param <E> 
 * @author rferranti
 */
public interface Predicate<E> {
    /**
     *
     * @param element
     * @return true if the element fulfills the predicate
     */
    public boolean test(E element);
}
