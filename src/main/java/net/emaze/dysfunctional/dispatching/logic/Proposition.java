package net.emaze.dysfunctional.dispatching.logic;

/**
 * A nullary functor returning a boolean
 *
 * @author rferranti
 */
public interface Proposition {

    /**
     * States a proposition.
     *
     * @return true or false
     */
    boolean state();
}
