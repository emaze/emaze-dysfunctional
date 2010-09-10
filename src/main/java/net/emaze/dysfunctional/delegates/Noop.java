package net.emaze.dysfunctional.delegates;

/**
 * A null unary functor with no return value effectively doing nothing
 * @author rferranti
 */
public class Noop<E> implements Action<E> {

    @Override
    public void perform(E t) {

    }

}
