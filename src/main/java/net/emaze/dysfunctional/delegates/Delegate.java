package net.emaze.dysfunctional.delegates;

/**
 * A unary functor
 * @param <R>
 * @param <T> 
 * @author rferranti
 */
public interface Delegate<R,T> {
    R perform(T t);
}
