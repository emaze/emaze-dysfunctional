package net.emaze.dysfunctional.options;

import java.util.Optional;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * Unary Predicate matching Optional.empty elements.
 * @param <T> the maybe type parameter
 * @author rferranti
 */
public class IsNothing<T> implements Predicate<Optional<T>> {

    @Override
    public boolean test(Optional<T> element) {
        dbc.precondition(element != null, "testing IsNothing against null");
        return !element.isPresent();
    }

}
