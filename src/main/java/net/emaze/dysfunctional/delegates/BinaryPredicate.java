package net.emaze.dysfunctional.delegates;

/**
 * A binary functor returning a boolean
 * @author rferranti
 */
public interface BinaryPredicate<T1,T2> {
    boolean call(T1 t1, T2 t2);
}
