package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * note this is not the wrapping delegate you usually want, look @ LiftJust
 * @author rferranti
 */
public class LiftMaybe<T> implements Delegate<Maybe<T>, T> {

    @Override
    public Maybe<T> perform(T valueOrNull) {
        if (valueOrNull == null) {
            return Maybe.nothing();
        }
        return Maybe.just(valueOrNull);
    }
}
