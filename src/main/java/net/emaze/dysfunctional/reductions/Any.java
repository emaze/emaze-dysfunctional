package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * A unary predicate yielding true if any of the iterator elements matches the
 * nested predicate.
 *
 * @param <T> the iterator element type
 * @author rferranti
 */
public class Any<T> implements Predicate<Iterator<T>> {

    private final Predicate<T> predicate;

    public Any(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot create Any with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call Any with a null iterator");
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                return true;
            }
        }
        return false;
    }
}
