package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;

/**
 * A binary function performing a multiplication.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class Multiply<R, T1, T2> implements BiFunction<T1, T2, R> {

    private final MultiplyPolicy<R, T1, T2> policy;

    public Multiply(MultiplyPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Multiply with a null multiply policy");
        this.policy = policy;
    }

    @Override
    public R apply(T1 accumulator, T2 value) {
        return policy.multiply(accumulator, value);
    }
}
