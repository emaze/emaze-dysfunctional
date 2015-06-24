package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Predicate;

/**
 * A unary predicate yielding true if every iterator element matches the nested
 * predicate.
 *
 * @param <T> the iterator element type
 * @author rferranti
 */
public class Every<T> implements Predicate<Iterator<T>> {

    private final Predicate<T> predicate;

    public Every(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot create Every with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean test(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call Every with a null iterator");
        while (iterator.hasNext()) {
            if (!predicate.test(iterator.next())) {
                return false;
            }
        }
        return true;
    }
}
