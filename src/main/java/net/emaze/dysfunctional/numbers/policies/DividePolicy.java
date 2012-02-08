package net.emaze.dysfunctional.numbers.policies;

/**
 * Division policy.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type.
 */
public interface DividePolicy<R, T1, T2> {

    R divide(T1 lhs, T2 rhs);
}
