package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Proposition;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a proposition capturing the result.
 *
 * @author rferranti
 */
public class CapturingProposition implements Proposition {

    private final Proposition nested;
    private final Box<Boolean> result;

    public CapturingProposition(Proposition nested, Box<Boolean> result) {
        dbc.precondition(nested != null, "cannot capture from a null proposition");
        dbc.precondition(result != null, "cann capture with a null result box");
        this.nested = nested;
        this.result = result;
    }

    @Override
    public boolean state() {
        final boolean stated = nested.state();
        result.setContent(stated);
        return stated;
    }
}
