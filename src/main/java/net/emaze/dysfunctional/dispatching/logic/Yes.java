package net.emaze.dysfunctional.dispatching.logic;

/**
 * We can.
 *
 * @author rferranti
 */
public class Yes implements Proposition {

    /**
     * Yields true.
     *
     * @return true
     */
    @Override
    public boolean state() {
        return true;
    }
}
