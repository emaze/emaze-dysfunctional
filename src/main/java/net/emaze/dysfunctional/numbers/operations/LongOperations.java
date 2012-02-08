package net.emaze.dysfunctional.numbers.operations;

import net.emaze.dysfunctional.numbers.policies.DividePolicy;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;

/**
 * Default long policies.
 *
 * @author rferranti
 */
public class LongOperations implements
        SumPolicy<Long, Long, Long>,
        SubtractPolicy<Long, Long, Long>,
        DividePolicy<Long, Long, Long>,
        MultiplyPolicy<Long, Long, Long>,
        ModulusPolicy<Long, Long, Integer> {

    @Override
    public Long sum(Long lhs, Long rhs) {
        return lhs + rhs;
    }

    @Override
    public Long subtract(Long lhs, Long rhs) {
        return lhs - rhs;
    }

    @Override
    public Long divide(Long lhs, Long rhs) {
        return lhs / rhs;
    }

    @Override
    public Long multiply(Long lhs, Long rhs) {
        return lhs * rhs;
    }

    @Override
    public Long modulus(Long lhs, Integer modulus) {
        return lhs % modulus;
    }
}
