package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for doubles.
 *
 * @author dangelocola
 */
public class AddOneDoubleSequencingPolicy implements SequencingPolicy<Double> {

    @Override
    public Maybe<Double> next(Double element) {
        dbc.precondition(element != null, "cannot get next of null from a AddOneDoubleSequencingPolicy");
        return (element + 1) < element ? Maybe.<Double>nothing() : Maybe.just(element + 1);
    }

    @Override
    public int hashCode() {
        return AddOneDoubleSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AddOneDoubleSequencingPolicy;
    }
}
