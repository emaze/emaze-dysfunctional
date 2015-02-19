package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.BiFunction;

/**
 *
 * @author rferranti
 * @param <T> the result type
 */
public class MaybeOrElse<T> implements BiFunction<Optional<T>, T, T> {

    @Override
    public T apply(Optional<T> maybe, T otherwise) {
        dbc.precondition(maybe != null, "cannot perform MaybeOrElse on a null Maybe");
        return maybe.orElse(otherwise);
    }
}
