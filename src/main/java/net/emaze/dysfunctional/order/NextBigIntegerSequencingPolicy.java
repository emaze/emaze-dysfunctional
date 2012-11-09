package net.emaze.dysfunctional.order;

import java.math.BigInteger;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for BigIntegers.
 *
 * @author rferranti
 */
public class NextBigIntegerSequencingPolicy implements SequencingPolicy<BigInteger> {

    @Override
    public Maybe<BigInteger> next(BigInteger element) {
        dbc.precondition(element != null, "cannot get next of null from a NextBigIntegerSequencingPolicy");
        return Maybe.just(element.add(BigInteger.ONE));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof NextBigIntegerSequencingPolicy;
    }

    @Override
    public int hashCode() {
        return NextBigIntegerSequencingPolicy.class.hashCode();
    }
}
