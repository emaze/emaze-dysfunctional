package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Transforms a maybe mapping Maybe.nothing to Either.left(leftTypeProvider())
 * and Maybe.just to Either.right.
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeToEither<L, R> implements Delegate<Either<L, R>, Maybe<R>> {

    private final Provider<L> leftTypeProvider;

    public MaybeToEither(Provider<L> leftTypeProvider) {
        dbc.precondition(leftTypeProvider != null, "cannot create MaybeToEither with a null left type provider");
        this.leftTypeProvider = leftTypeProvider;
    }

    @Override
    public Either<L, R> perform(Maybe<R> maybe) {
        dbc.precondition(maybe != null, "cannot transform a null maybe to an either");
        return maybe.either(leftTypeProvider);
    }
}
