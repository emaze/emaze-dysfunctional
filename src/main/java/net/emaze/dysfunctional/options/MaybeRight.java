package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeRight<L, R> implements Function<Either<L, R>, Optional<R>> {

    @Override
    public Optional<R> apply(Either<L, R> either) {
        return either.maybe();
    }
}
