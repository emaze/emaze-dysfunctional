package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public class Always<E> implements Predicate<E>{

    @Override
    public boolean test(E element) {
        return true;
    }

}
