package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a proposition to a predicate. Adapting is performed by ignoring the
 * parameter of the adapted proposition.
 *
 * @param <T> the adapter parameter type
 * @author rferranti
 */
public class PropositionIgnoreParameter<T> implements Predicate<T> {

    private final Proposition proposition;

    public PropositionIgnoreParameter(Proposition proposition) {
        dbc.precondition(proposition != null, "cannot ignore parameter with a null proposition");
        this.proposition = proposition;
    }

    @Override
    public boolean accept(T parameter) {
        return proposition.state();
    }
}
