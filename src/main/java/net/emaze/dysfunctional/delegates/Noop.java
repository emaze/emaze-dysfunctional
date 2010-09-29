package net.emaze.dysfunctional.delegates;

/**
 * A null unary functor with no return value effectively doing nothing
 * @param <E> 
 * @author rferranti
 */
public class Noop<E> implements Action<E> {

    /**
     * Does nothing 
     * @param element the element we ignore to do nothing
     */
    @Override
    public void perform(E element) {

    }

}
