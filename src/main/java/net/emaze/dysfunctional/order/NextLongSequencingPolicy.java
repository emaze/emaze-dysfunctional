package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for longs.
 *
 * @author rferranti
 */
public class NextLongSequencingPolicy implements SequencingPolicy<Long> {

    @Override
    public Maybe<Long> next(Long element) {
        dbc.precondition(element != null, "cannot get next of null from a NextLongSequencingPolicy");
        return element.equals(Long.MAX_VALUE) ? Maybe.<Long>nothing() : Maybe.just(element + 1);
    }

    @Override
    public int hashCode() {
        return NextLongSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NextLongSequencingPolicy;
    }
}
