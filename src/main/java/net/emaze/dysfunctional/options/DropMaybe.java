package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;

/**
 * note this is not the unwrapping delegate you usually want, look at FromJust.
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
