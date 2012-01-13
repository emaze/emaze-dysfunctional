package net.emaze.dysfunctional.dispatching.logic;

/**
 * A nullary functor returning a boolean
 *
 * @author rferranti
 */
public interface Proposition {

    /**
     * states a proposition.
     *
     * @return true or false
     */
    boolean state();
}
