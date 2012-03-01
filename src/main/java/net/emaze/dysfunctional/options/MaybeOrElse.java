package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 * 
 * @author rferranti
 * @param <T> the result type
 */
public class MaybeOrElse<T> implements BinaryDelegate<T, Maybe<T>, T> {

    @Override
    public T perform(Maybe<T> maybe, T otherwise) {
        dbc.precondition(maybe != null, "cannot perform MaybeOrElse on a null Maybe");
        return maybe.orElse(otherwise);
    }
}
