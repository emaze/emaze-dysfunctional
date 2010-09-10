package net.emaze.dysfunctional.delegates;

/**
 * A binary functor
 * @author rferranti
 */
public interface BinaryDelegate<R,T1,T2> {
    public R perform(T1 t1,T2 t2);
}
