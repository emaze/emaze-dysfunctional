package net.emaze.disfunctional.iterations;

import java.math.BigDecimal;

/**
 *
 * @author rferranti
 */
public interface SequencingPolicy<T> {
    T next(T element);

    public static SequencingPolicy<Integer> INTEGER_POLICY = new SequencingPolicy<Integer>()
    {
        public Integer next(Integer element) {
            return ++element;
        }
    };
    public static SequencingPolicy<Long> LONG_POLICY = new SequencingPolicy<Long>()
    {
        public Long next(Long element) {
            return ++element;
        }
    };
    public static SequencingPolicy<BigDecimal> BIGDECIMAL_POLICY = new SequencingPolicy<BigDecimal>()
    {
        public BigDecimal next(BigDecimal element) {
            return element.add(BigDecimal.ONE);
        }
    };

}
