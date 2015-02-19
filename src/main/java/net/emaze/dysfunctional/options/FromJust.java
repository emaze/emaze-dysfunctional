package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Unary Function transforming a just(T) to T.
 *
 * @param <T> the maybe type parameter, the resulting type
 * @author dangelocola, rferranti
 */
public class FromJust<T> implements Function<Maybe<T>, T> {

    @Override
    public T apply(Maybe<T> element) {
        dbc.precondition(element != null, "performing FromJust against null");
        return element.value();
    }
}
