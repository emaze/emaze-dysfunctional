package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.BooleanSupplier;

/**
 * We can.
 *
 * @author rferranti
 */
public class Yes implements BooleanSupplier {

    /**
     * Yields true.
     *
     * @return true
     */
    @Override
    public boolean getAsBoolean() {
        return true;
    }
}
