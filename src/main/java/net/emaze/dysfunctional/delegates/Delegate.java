package net.emaze.dysfunctional.delegates;

/**
 * A unary functor
 * @author rferranti
 */
public interface Delegate<R,T> {
    R perform(T t);
}
