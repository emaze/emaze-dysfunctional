package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeRight<L, R> implements Function<Either<L, R>, Maybe<R>> {

    @Override
    public Maybe<R> apply(Either<L, R> either) {
        return either.maybe();
    }
}
