package net.emaze.dysfunctional.numbers;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;
import net.emaze.dysfunctional.numbers.policies.ModulusPolicy;

/**
 * A unary predicate evaluating if an element is even.
 *
 * @author rferranti
 * @param <R> the ModulusPolicy result type
 * @param <T> the parameter type
 */
public class IsEven<R, T> implements Predicate<T> {

    private final ModulusPolicy<R, T, Integer> policy;
    private final R zero;

    public IsEven(ModulusPolicy<R, T, Integer> policy, R zero) {
        dbc.precondition(policy != null, "cannot create IsEven with a null modulus policy");
        this.policy = policy;
        this.zero = zero;
    }

    @Override
    public boolean accept(T value) {
        return policy.modulus(value, 2).equals(zero);
    }
}
