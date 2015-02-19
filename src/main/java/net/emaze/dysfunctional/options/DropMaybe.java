package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Unwraps a Optional<T> transforming a empty(T) to null, of(T) to T.
 *
 * Note this is not the unwrapping delegate you usually want, look at FromJust.
 * Adjoint of LiftMaybe.
 *
 * @param <T> the result type and maybe type parameter
 * @author rferranti
 */
public class DropMaybe<T> implements Function<Optional<T>, T> {

    @Override
    public T apply(Optional<T> maybe) {
        dbc.precondition(maybe != null, "performing DropMaybe on null");
        if (!maybe.isPresent()) {
            return null;
        }
        return maybe.get();
    }
}
