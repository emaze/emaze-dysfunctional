package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.DividePolicy;

/**
 * A binary function performing a division.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class Divide<R, T1, T2> implements BiFunction<T1, T2, R> {

    private final DividePolicy<R, T1, T2> policy;

    public Divide(DividePolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Divide with a null divide policy");
        this.policy = policy;
    }

    @Override
    public R apply(T1 accumulator, T2 value) {
        return policy.divide(accumulator, value);
    }
}
