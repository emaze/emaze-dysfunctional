package net.emaze.dysfunctional.dispatching.logic;

import java.util.function.BooleanSupplier;

/**
 * You don't.
 *
 * @author rferranti
 */
public class No implements BooleanSupplier {

    /**
     * Yields false.
     *
     * @return false
     */
    @Override
    public boolean getAsBoolean() {
        return false;
    }
}
