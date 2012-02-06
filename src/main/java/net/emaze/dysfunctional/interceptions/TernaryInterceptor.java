package net.emaze.dysfunctional.interceptions;

/**
 * A ternary inner automorphism.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @param <T3> the third parameter type
 * @author rferranti
 */
public interface TernaryInterceptor<T1, T2, T3> {

    void before(T1 first, T2 second, T3 third);

    void after(T1 first, T2 second, T3 third);
}
