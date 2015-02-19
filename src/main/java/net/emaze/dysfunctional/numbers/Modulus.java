package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;

/**
 * A binary delegate evaluating modulus of a type.
 *
 * @author rferranti
 * @param <R> the ModulusPolicy result type
 * @param <T1> the accumulator type
 * @param <T2> the modulus type
 */
public class Modulus<R, T1, T2> implements BiFunction<T1, T2, R> {

    private final ModulusPolicy<R, T1, T2> policy;

    public Modulus(ModulusPolicy<R, T1, T2> policy) {
        dbc.precondition(policy != null, "cannot create Modulus with a null modulus policy");
        this.policy = policy;
    }

    @Override
    public R apply(T1 accumulator, T2 value) {
        return policy.modulus(accumulator, value);
    }
}
