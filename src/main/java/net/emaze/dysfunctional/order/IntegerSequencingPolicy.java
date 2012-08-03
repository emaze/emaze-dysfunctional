package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for big integers.
 *
 * @author rferranti
 */
public class IntegerSequencingPolicy implements SequencingPolicy<Integer> {

    @Override
    public Maybe<Integer> next(Integer element) {
        dbc.precondition(element != null, "cannot get next of null from a IntegerSequencingPolicy");
        return element.equals(Integer.MAX_VALUE) ? Maybe.<Integer>nothing() : Maybe.just(element + 1);
    }

    @Override
    public Integer prev(Integer element) {
        dbc.precondition(element != null, "cannot get prev of null from a IntegerSequencingPolicy");
        return element - 1;
    }

    @Override
    public int hashCode() {
        return IntegerSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerSequencingPolicy;
    }
}
