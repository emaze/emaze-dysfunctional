package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.numbers.policies.DividePolicy;

public class Divide<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final DividePolicy<R, T1, T2> policy;

    public Divide(DividePolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Divide with a null divide policy");
        this.policy = policy;
    }

    @Override
    public R perform(T1 accumulator, T2 value) {
        return policy.divide(accumulator, value);
    }
}
