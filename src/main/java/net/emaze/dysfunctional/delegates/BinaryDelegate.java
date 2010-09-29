package net.emaze.dysfunctional.delegates;

/**
 * A binary functor
 * @param <R>
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public interface BinaryDelegate<R,T1,T2> {
    public R perform(T1 t1,T2 t2);
}
