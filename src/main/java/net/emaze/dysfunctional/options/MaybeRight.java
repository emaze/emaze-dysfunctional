package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeRight<L, R> implements Delegate<Maybe<R>, Either<L, R>> {

    @Override
    public Maybe<R> perform(Either<L, R> either) {
        return either.maybe();
    }
}
