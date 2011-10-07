package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.ConstantDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

public class MaybeLeft<L, R> implements Delegate<Maybe<L>, Either<L, R>> {

    @Override
    public Maybe<L> perform(Either<L, R> either) {
        final Maybe<L> nothing = Maybe.<L>nothing();
        return either.fmap(new LiftJust<L>(), new ConstantDelegate<Maybe<L>, R>(nothing));
    }
    
}
