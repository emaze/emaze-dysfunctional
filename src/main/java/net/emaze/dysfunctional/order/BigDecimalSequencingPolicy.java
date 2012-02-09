package net.emaze.dysfunctional.order;

import java.math.BigDecimal;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * A sequencing policy for big decimals.
 *
 * @author rferranti
 */
public class BigDecimalSequencingPolicy implements SequencingPolicy<BigDecimal> {

    @Override
    public BigDecimal next(BigDecimal element) {
        dbc.precondition(element != null, "cannot get next of null from a BigDecimalSequencingPolicy");
        return element.add(BigDecimal.ONE);
    }

    @Override
    public BigDecimal prev(BigDecimal element) {
        dbc.precondition(element != null, "cannot get prev of null from a BigDecimalSequencingPolicy");
        return element.subtract(BigDecimal.ONE);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BigDecimalSequencingPolicy;
    }

    @Override
    public int hashCode() {
        return BigDecimalSequencingPolicy.class.hashCode();
    }
}
