package net.emaze.disfunctional.iterations.sequencing;

import java.math.BigDecimal;

/**
 *
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

}
