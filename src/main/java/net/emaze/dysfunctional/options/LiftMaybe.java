package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 * Transforms a T to a Maybe monadic value yielding nothing(T) for nulls and
 * just(T) otherwise.
 *
 * Note this is not the wrapping delegate you usually want, look @ PureMaybe.
 * Adjoint functor of DropMaybe.
 *
 * @author rferranti
 */
public class LiftMaybe<T> implements Function<T, Maybe<T>> {

    @Override
    public Maybe<T> apply(T valueOrNull) {
        if (valueOrNull == null) {
            return Maybe.nothing();
        }
        return Maybe.just(valueOrNull);
    }
}
