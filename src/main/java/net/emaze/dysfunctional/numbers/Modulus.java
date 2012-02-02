package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;

public class Modulus<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final ModulusPolicy<R, T1, T2> policy;

    public Modulus(ModulusPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Modulus with a null modulus policy");
        this.policy = policy;
    }

    @Override
    public R perform(T1 accumulator, T2 value) {
        return policy.modulus(accumulator, value);
    }
}
