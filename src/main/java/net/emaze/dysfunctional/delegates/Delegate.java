package net.emaze.dysfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Delegate<R,T> {
    R perform(T t);
}
