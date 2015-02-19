package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;

/**
 * Pointed.pure() implementation of the {@literal Optional<T>} functor.
 *
 * @author rferranti
 * @param <T> the get type
 */
public class PureMaybe<T> implements Function<T, Optional<T>> {

    @Override
    public Optional<T> apply(T value) {
        return Optional.of(value);
    }
}
