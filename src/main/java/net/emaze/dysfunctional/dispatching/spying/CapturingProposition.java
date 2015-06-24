package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BooleanSupplier;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a proposition capturing the result.
 *
 * @author rferranti
 */
public class CapturingProposition implements BooleanSupplier {

    private final BooleanSupplier nested;
    private final Box<Boolean> result;

    public CapturingProposition(BooleanSupplier nested, Box<Boolean> result) {
        dbc.precondition(nested != null, "cannot capture from a null proposition");
        dbc.precondition(result != null, "cann capture with a null result box");
        this.nested = nested;
        this.result = result;
    }

    @Override
    public boolean getAsBoolean() {
        final boolean stated = nested.getAsBoolean();
        result.setContent(stated);
        return stated;
    }
}
