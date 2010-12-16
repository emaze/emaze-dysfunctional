package net.emaze.dysfunctional.delegates;

/**
 * A ternary functor returning a boolean
 * @param <T1> the first element Type
 * @param <T2> the second element Type
 * @param <T3> the third element Type
 * @author rferranti
 */
public interface TernaryPredicate<T1,T2, T3> {
    boolean call(T1 first, T2 second, T3 third);
}
