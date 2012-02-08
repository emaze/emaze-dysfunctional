package net.emaze.dysfunctional.interceptions;

/**
 * A binary inner automorphism.
 *
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 * @author rferranti
 */
public interface BinaryInterceptor<T1, T2> {

    void before(T1 first, T2 second);

    void after(T1 first, T2 second);
}
