package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Predicate;

/**
 * Unary Predicate matching Maybe.just elements
 * @author dangelocola, rferranti
 */
public class IsJust<T> implements Predicate<Maybe<T>> {

    @Override
    public boolean test(Maybe<T> element) {
        dbc.precondition(element != null, "testing IsJust against null");
        return element.hasValue();
    }

}
