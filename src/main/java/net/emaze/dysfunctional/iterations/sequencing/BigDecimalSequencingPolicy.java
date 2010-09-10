package net.emaze.dysfunctional.iterations.sequencing;

import java.math.BigDecimal;
import net.emaze.dysfunctional.contracts.dbc;

public class BigDecimalSequencingPolicy implements SequencingPolicy<BigDecimal> {

    @Override
    public BigDecimal next(BigDecimal element) {
        dbc.precondition(element != null, "cannot get next of null from a BigDecimalSequencingPolicy");
        return element.add(BigDecimal.ONE);
    }
}
