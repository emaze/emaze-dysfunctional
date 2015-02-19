package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 * Pointed.pure() implementation of the {@literal Maybe<T>} functor.
 *
 * @author rferranti
 * @param <T> the value type
 */
public class PureMaybe<T> implements Function<T, Maybe<T>> {

    @Override
    public Maybe<T> apply(T value) {
        return Maybe.just(value);
    }
}
