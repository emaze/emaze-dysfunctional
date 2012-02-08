package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Unwraps a Maybe<T> transforming a nothing(T) to null, just(T) to T.
 *
 * Note this is not the unwrapping delegate you usually want, look at FromJust.
 * Adjoint of LiftMaybe.
 *
 * @param <T> the result type and maybe type parameter
 * @author rferranti
 */
public class DropMaybe<T> implements Delegate<T, Maybe<T>> {

    @Override
    public T perform(Maybe<T> maybe) {
        dbc.precondition(maybe != null, "performing DropMaybe on null");
        if (!maybe.hasValue()) {
            return null;
        }
        return maybe.value();
    }
}
