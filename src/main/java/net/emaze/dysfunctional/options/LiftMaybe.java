package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Transforms a T to a Maybe monadic value yielding nothing(T) for nulls and
 * just(T) otherwise.
 *
 * Note this is not the wrapping delegate you usually want, look @ PureMaybe.
 * Adjoint functor of DropMaybe.
 *
 * @author rferranti
 */
public class LiftMaybe<T> implements Delegate<Maybe<T>, T> {

    @Override
    public Maybe<T> perform(T valueOrNull) {
        if (valueOrNull == null) {
            return Maybe.nothing();
        }
        return Maybe.just(valueOrNull);
    }
}
