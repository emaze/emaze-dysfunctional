package net.emaze.dysfunctional.order;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.Optional;

/**
 * A sequencing policy for big integers.
 *
 * @author rferranti
 */
public class NextIntegerSequencingPolicy implements SequencingPolicy<Integer> {

    @Override
    public Optional<Integer> next(Integer element) {
        dbc.precondition(element != null, "cannot get next of null from a NextIntegerSequencingPolicy");
        return element.equals(Integer.MAX_VALUE) ? Optional.<Integer>empty() : Optional.of(element + 1);
    }

    @Override
    public int hashCode() {
        return NextIntegerSequencingPolicy.class.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NextIntegerSequencingPolicy;
    }
}
