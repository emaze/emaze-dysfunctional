package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.numbers.policies.SubtractPolicy;

/**
 * A binary delegate performing a subtraction
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class Subtract<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final SubtractPolicy<R, T1, T2> policy;

    public Subtract(SubtractPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Subtract with a null subtract policy");
        this.policy = policy;
    }

    @Override
    public R perform(T1 accumulator, T2 value) {
        return policy.subtract(accumulator, value);
    }
}
