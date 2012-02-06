package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Pointed.pure() implementation of the Either<LT,RT> Bifunctor.
 *
 * @author rferranti
 * @param <LT> the right type
 * @param <RT> the left type
 */
public class PureEither<LT, RT> implements Delegate<Either<LT, RT>, RT> {

    @Override
    public Either<LT, RT> perform(RT value) {
        return Either.right(value);
    }
}
