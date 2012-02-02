package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class MaybeLeft<L, R> implements Delegate<Maybe<L>, Either<L, R>> {

    @Override
    public Maybe<L> perform(Either<L, R> either) {
        return either.flip().maybe();
    }
    
}
