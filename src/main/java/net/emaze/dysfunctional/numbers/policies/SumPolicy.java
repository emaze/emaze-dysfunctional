package net.emaze.dysfunctional.numbers.policies;

/**
 * Sum policy.
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type.
 */
public interface SumPolicy<R, T1, T2> {

    R sum(T1 lhs, T2 rhs);
}
