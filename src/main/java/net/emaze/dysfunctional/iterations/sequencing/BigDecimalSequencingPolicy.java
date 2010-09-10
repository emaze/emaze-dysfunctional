package net.emaze.dysfunctional.iterations.sequencing;

import java.math.BigDecimal;

public class BigDecimalSequencingPolicy implements SequencingPolicy<BigDecimal> {

    @Override
    public BigDecimal next(BigDecimal element) {
        return element.add(BigDecimal.ONE);
    }
}
