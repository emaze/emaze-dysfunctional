package net.emaze.disfunctional.delegates;

/**
 *
 * @author rferranti
 */
public interface Delegate<R,T> {
    R perform(T t);
}
