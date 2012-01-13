package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a Proposition to a Predicate<T> ignoring the parameter.
 * @param <T>
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
