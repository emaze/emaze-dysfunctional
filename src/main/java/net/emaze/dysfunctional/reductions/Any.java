package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @author rferranti
 */
public class Any<T> implements Predicate<Iterator<T>> {

    private final Predicate<T> predicate;

    public Any(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot create Any with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call Any with a null iterator");
        while (iterator.hasNext()) {
            if (predicate.accept(iterator.next())) {
                return true;
            }
        }
        return false;
    }
}
