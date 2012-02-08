package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Transforms an either to a maybe containing just() right type or nothing().
 *
 * @author rferranti
 * @param <LT> the left type parameter
 * @param <RT> the right type parameter
 */
public class EitherToMaybe<LT, RT> implements Delegate<Maybe<RT>, Either<LT, RT>> {

    @Override
    public Maybe<RT> perform(Either<LT, RT> either) {
        dbc.precondition(either != null, "cannot transform a null either to a maybe");
        return either.maybe();
    }
}
