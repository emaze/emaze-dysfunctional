package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;
import net.emaze.dysfunctional.dispatching.logic.Proposition;

/**
 * Adapts a proposition to a predicate. Adapting is performed by ignoring the
 * parameter of the adapted proposition.
 *
 * @param <T> the adapter parameter type
 * @author rferranti
 */
public class PropositionIgnoreParameter<T> implements Predicate<T> {

    private final Proposition adapted;

    public PropositionIgnoreParameter(Proposition adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore parameter of a null proposition");
        this.adapted = adaptee;
    }

    @Override
    public boolean test(T parameter) {
        return adapted.state();
    }
}
