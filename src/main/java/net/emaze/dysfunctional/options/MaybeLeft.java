package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 *
 * @author rferranti
 * @param <L> the either left type parameter
 * @param <R> the either right type parameter
 */
public class MaybeLeft<L, R> implements Delegate<Maybe<L>, Either<L, R>> {

    @Override
    public Maybe<L> perform(Either<L, R> either) {
        return either.flip().maybe();
    }
}
