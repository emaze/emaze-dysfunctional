package net.emaze.dysfunctional.numbers.operations;

import net.emaze.dysfunctional.numbers.policies.DividePolicy;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;

public class FloatOperations implements
        SumPolicy<Float, Float, Float>,
        SubtractPolicy<Float, Float, Float>,
        DividePolicy<Float, Float, Float>,
        MultiplyPolicy<Float, Float, Float> {

    @Override
    public Float sum(Float lhs, Float rhs) {
        return lhs + rhs;
    }

    @Override
    public Float subtract(Float lhs, Float rhs) {
        return lhs - rhs;
    }

    @Override
    public Float divide(Float lhs, Float rhs) {
        return lhs / rhs;
    }

    @Override
    public Float multiply(Float lhs, Float rhs) {
        return lhs * rhs;
    }
}
