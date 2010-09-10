package net.emaze.dysfunctional.delegates;

/**
 * a Null Unary Predicate always returning true
 * @author rferranti
 */
public class Always<E> implements Predicate<E>{

    @Override
    public boolean test(E element) {
        return true;
    }

}
