package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;
import net.emaze.dysfunctional.dispatching.delegates.Provider;

/**
 * Transforms a maybe mapping nothing to Either.left(leftTypeProvider()) and
 * just() to Either.right.
 *
 * @author rferranti
 * @param <LT>
 * @param <RT>
 */
public class MaybeToEither<LT, RT> implements Delegate<Either<LT, RT>, Maybe<RT>> {

    private final Provider<LT> leftTypeProvider;

    public MaybeToEither(Provider<LT> leftTypeProvider) {
        dbc.precondition(leftTypeProvider != null, "cannot create MaybeToEither with a null left type provider");
        this.leftTypeProvider = leftTypeProvider;
    }

    @Override
    public Either<LT, RT> perform(Maybe<RT> maybe) {
        dbc.precondition(maybe != null, "cannot transform a null maybe to an either");
        return maybe.either(leftTypeProvider);
    }
}
