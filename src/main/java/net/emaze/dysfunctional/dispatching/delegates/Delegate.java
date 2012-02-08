package net.emaze.dysfunctional.dispatching.delegates;

/**
 * A unary functor.
 *
 * @param <R> the delegate result type
 * @param <T> the delegate parameter type
 * @author rferranti
 */
public interface Delegate<R, T> {

    R perform(T t);
}
