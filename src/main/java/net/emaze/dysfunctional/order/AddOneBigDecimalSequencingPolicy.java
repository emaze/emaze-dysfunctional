package net.emaze.dysfunctional.order;

import java.math.BigDecimal;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.options.Maybe;

/**
 * A sequencing policy for big decimals.
 *
 * @author rferranti
 */
public class AddOneBigDecimalSequencingPolicy implements SequencingPolicy<BigDecimal> {

    @Override
    public Maybe<BigDecimal> next(BigDecimal element) {
        dbc.precondition(element != null, "cannot get next of null from a AddOneBigDecimalSequencingPolicy");
        return Maybe.just(element.add(BigDecimal.ONE));
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AddOneBigDecimalSequencingPolicy;
    }

    @Override
    public int hashCode() {
        return AddOneBigDecimalSequencingPolicy.class.hashCode();
    }
}
