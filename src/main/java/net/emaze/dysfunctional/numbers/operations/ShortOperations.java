package net.emaze.dysfunctional.numbers.operations;

import net.emaze.dysfunctional.numbers.policies.DividePolicy;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;

/**
 * Default short policies.
 *
 * @author rferranti
 */
public class ShortOperations implements
        SumPolicy<Short, Short, Short>,
        SubtractPolicy<Short, Short, Short>,
        DividePolicy<Short, Short, Short>,
        MultiplyPolicy<Short, Short, Short>,
        ModulusPolicy<Short, Short, Integer> {

    @Override
    public Short sum(Short lhs, Short rhs) {
        return (short) (lhs + rhs);
    }

    @Override
    public Short subtract(Short lhs, Short rhs) {
        return (short) (lhs - rhs);
    }

    @Override
    public Short divide(Short lhs, Short rhs) {
        return (short) (lhs / rhs);
    }

    @Override
    public Short multiply(Short lhs, Short rhs) {
        return (short) (lhs * rhs);
    }

    @Override
    public Short modulus(Short lhs, Integer modulus) {
        return (short) (lhs % modulus);
    }
}
