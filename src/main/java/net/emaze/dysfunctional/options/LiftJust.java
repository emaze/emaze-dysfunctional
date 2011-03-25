package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * @author rferranti
 */
public class LiftJust<T> implements Delegate<Maybe<T>, T> {

    @Override
    public Maybe<T> perform(T valueOrNull) {
        return Maybe.just(valueOrNull);
    }
}
