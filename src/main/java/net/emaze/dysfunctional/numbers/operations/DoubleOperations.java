package net.emaze.dysfunctional.numbers.operations;

import net.emaze.dysfunctional.numbers.policies.DividePolicy;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;

/**
 * Default double policies.
 * @author rferranti
 */
public class DoubleOperations implements
        SumPolicy<Double, Double, Double>,
        SubtractPolicy<Double, Double, Double>,
        DividePolicy<Double, Double, Double>,
        MultiplyPolicy<Double, Double, Double> {

    @Override
    public Double sum(Double lhs, Double rhs) {
        return lhs + rhs;
    }

    @Override
    public Double subtract(Double lhs, Double rhs) {
        return lhs - rhs;
    }

    @Override
    public Double divide(Double lhs, Double rhs) {
        return lhs / rhs;
    }

    @Override
    public Double multiply(Double lhs, Double rhs) {
        return lhs * rhs;
    }
}
