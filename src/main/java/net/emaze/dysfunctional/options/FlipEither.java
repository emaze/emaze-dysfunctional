package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Flips an either.
 *
 * @author rferranti
 * @param <LT> the source either left type parameter
 * @param <RT> the source either right type parameter
 */
public class FlipEither<LT, RT> implements Delegate<Either<RT, LT>, Either<LT, RT>> {

    @Override
    public Either<RT, LT> perform(Either<LT, RT> either) {
        dbc.precondition(either != null, "cannot flip a null either");
        return either.flip();
    }
}