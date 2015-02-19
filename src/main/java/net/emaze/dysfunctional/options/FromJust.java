package net.emaze.dysfunctional.options;

import java.util.Optional;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Unary function transforming a of(T) to T.
 *
 * @param <T> the maybe type parameter, the resulting type
 * @author dangelocola, rferranti
 */
public class FromJust<T> implements Function<Optional<T>, T> {

    @Override
    public T apply(Optional<T> element) {
        dbc.precondition(element != null, "performing FromJust against null");
        return element.get();
    }
}
