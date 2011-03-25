package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A unary functor
 * @param <R>
 * @param <T> 
 * @author rferranti
 */
public interface Delegate<R,T> {
    R perform(T t);
}
