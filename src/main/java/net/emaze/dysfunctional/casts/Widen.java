package net.emaze.dysfunctional.casts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * @author rferranti
 * @param <R>
 * @param <T> 
 */
public class Widen<R, T> implements Delegate<R, T> {

    @Override
    public R perform(T value) {
        return (R) value;
    }

}
