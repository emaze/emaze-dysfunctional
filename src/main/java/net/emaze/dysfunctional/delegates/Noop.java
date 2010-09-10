package net.emaze.dysfunctional.delegates;

/**
 * A (Null|Placebo)Action, effectively does nothing
 * @author rferranti
 */
public class Noop<E> implements Action<E> {

    @Override
    public void perform(E t) {

    }

}
