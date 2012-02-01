package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class MaybeRight<L, R> implements Delegate<Maybe<R>, Either<L, R>> {

    @Override
    public Maybe<R> perform(Either<L, R> either) {
        return either.maybe();
    }
    
}
