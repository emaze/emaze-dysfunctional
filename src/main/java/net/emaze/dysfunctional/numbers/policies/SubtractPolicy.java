package net.emaze.dysfunctional.numbers.policies;

/**
 * Subtraction policy.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type.
 */
public interface SubtractPolicy<R, T1, T2> {

    R subtract(T1 lhs, T2 rhs);
}
