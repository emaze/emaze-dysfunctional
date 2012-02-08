package net.emaze.dysfunctional.numbers.policies;

/**
 * Modulus policy.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type.
 */
public interface ModulusPolicy<R, T1, T2> {

    R modulus(T1 lhs, T2 modulus);
}
