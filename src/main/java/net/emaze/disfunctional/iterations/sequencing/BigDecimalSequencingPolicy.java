package net.emaze.disfunctional.iterations.sequencing;

import java.math.BigDecimal;

public class BigDecimalSequencingPolicy implements SequencingPolicy<BigDecimal> {

    public BigDecimal next(BigDecimal element) {
        return element.add(BigDecimal.ONE);
    }
}
