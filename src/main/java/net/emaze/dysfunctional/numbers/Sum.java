package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.numbers.policies.SumPolicy;
import java.util.function.BiFunction;

/**
 * Sums two values.
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class Sum<R, T1, T2> implements BiFunction<T1, T2, R> {

    private final SumPolicy<R, T1, T2> policy;

    public Sum(SumPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Sum with a null sum policy");
        this.policy = policy;
    }

    @Override
    public R apply(T1 accumulator, T2 value) {
        return policy.sum(accumulator, value);
    }
}
