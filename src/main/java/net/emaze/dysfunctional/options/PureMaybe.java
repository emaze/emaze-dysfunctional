package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Pointed.pure() implementation of the Maybe<T> functor.
 * @author rferranti
 * @param <T> the value type
 */
public class PureMaybe<T> implements Delegate<Maybe<T>, T> {

    @Override
    public Maybe<T> perform(T value) {
        return Maybe.just(value);
    }
}
