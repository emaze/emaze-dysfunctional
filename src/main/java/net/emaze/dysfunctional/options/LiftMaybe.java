package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;

/**
 * Transforms a T to a Optional monadic get yielding empty(T) for nulls and
 of(T) otherwise.
 *
 * Note this is not the wrapping delegate you usually want, look @ PureMaybe.
 * Adjoint functor of DropMaybe.
 *
 * @author rferranti
 */
public class LiftMaybe<T> implements Function<T, Optional<T>> {

    @Override
    public Optional<T> apply(T valueOrNull) {
        if (valueOrNull == null) {
            return Optional.empty();
        }
        return Optional.of(valueOrNull);
    }
}
