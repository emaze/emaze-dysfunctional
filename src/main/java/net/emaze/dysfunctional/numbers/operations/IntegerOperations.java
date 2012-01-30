package net.emaze.dysfunctional.numbers.operations;

import net.emaze.dysfunctional.numbers.policies.DividePolicy;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;

public class IntegerOperations implements
        SumPolicy<Integer, Integer, Integer>,
        SubtractPolicy<Integer, Integer, Integer>,
        DividePolicy<Integer, Integer, Integer>,
        MultiplyPolicy<Integer, Integer, Integer>,
        ModulusPolicy<Integer, Integer, Integer> {

    @Override
    public Integer sum(Integer lhs, Integer rhs) {
        return lhs + rhs;
    }

    @Override
    public Integer subtract(Integer lhs, Integer rhs) {
        return lhs - rhs;
    }

    @Override
    public Integer divide(Integer lhs, Integer rhs) {
        return lhs / rhs;
    }

    @Override
    public Integer multiply(Integer lhs, Integer rhs) {
        return lhs * rhs;
    }

    @Override
    public Integer modulus(Integer lhs, Integer modulus) {
        return lhs % modulus;
    }
}
