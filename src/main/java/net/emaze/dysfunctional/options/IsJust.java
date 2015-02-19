package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * Unary Predicate matching Maybe.just elements.
 *
 * @param <T> the maybe type parameter
 * @author dangelocola, rferranti
 */
public class IsJust<T> implements Predicate<Maybe<T>> {

    @Override
    public boolean test(Maybe<T> element) {
        dbc.precondition(element != null, "testing IsJust against null");
        return element.hasValue();
    }
}
