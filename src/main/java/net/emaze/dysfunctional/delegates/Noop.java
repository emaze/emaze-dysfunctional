package net.emaze.dysfunctional.delegates;

/**
 * A null unary functor with no return value effectively doing nothing
 * @author rferranti
 */
public class Noop<E> implements Action<E> {

    /**
     * Does nothing 
     * @param element 
     */
    @Override
    public void perform(E element) {

    }

}
