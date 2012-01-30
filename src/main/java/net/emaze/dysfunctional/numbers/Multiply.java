package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.numbers.policies.MultiplyPolicy;

public class Multiply<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final MultiplyPolicy<R, T1, T2> policy;

    public Multiply(MultiplyPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Multiply with a null multiply policy");
        this.policy = policy;
    }

    @Override
    public R perform(T1 accumulator, T2 value) {
        return policy.multiply(accumulator, value);
    }
}
