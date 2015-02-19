package net.emaze.dysfunctional.options;

import java.util.function.Function;

/**
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeLeft<L, R> implements Function<Either<L, R>, Maybe<L>> {

    @Override
    public Maybe<L> apply(Either<L, R> either) {
        return either.flip().maybe();
    }
}
