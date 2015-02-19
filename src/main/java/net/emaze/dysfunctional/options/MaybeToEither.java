package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Transforms a maybe mapping Maybe.nothing to Either.left(leftTypeProvider())
 * and Maybe.just to Either.right.
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeToEither<L, R> implements Function<Maybe<R>, Either<L, R>> {

    private final Supplier<L> leftTypeProvider;

    public MaybeToEither(Supplier<L> leftTypeProvider) {
        dbc.precondition(leftTypeProvider != null, "cannot create MaybeToEither with a null left type provider");
        this.leftTypeProvider = leftTypeProvider;
    }

    @Override
    public Either<L, R> apply(Maybe<R> maybe) {
        dbc.precondition(maybe != null, "cannot transform a null maybe to an either");
        return maybe.either(leftTypeProvider);
    }
}
