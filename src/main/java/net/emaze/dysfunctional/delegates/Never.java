package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public class Never<E> implements Predicate<E>{

    @Override
    public boolean test(E element) {
        return false;
    }

}
