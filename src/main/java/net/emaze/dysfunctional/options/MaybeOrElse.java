package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 *
 * @author rferranti
 * @param <T> the result type
 */
public class MaybeOrElse<T> implements BiFunction<Maybe<T>, T, T> {

    @Override
    public T apply(Maybe<T> maybe, T otherwise) {
        dbc.precondition(maybe != null, "cannot perform MaybeOrElse on a null Maybe");
        return maybe.orElse(otherwise);
    }
}
