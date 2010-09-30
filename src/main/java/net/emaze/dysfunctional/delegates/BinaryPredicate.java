package net.emaze.dysfunctional.delegates;

/**
 * A binary functor returning a boolean
 * @param <T1> the former element Type
 * @param <T2> the latter element Type
 * @author rferranti
 */
public interface BinaryPredicate<T1,T2> {
    boolean call(T1 former, T2 latter);
}
