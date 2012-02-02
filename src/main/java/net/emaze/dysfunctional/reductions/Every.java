package net.emaze.dysfunctional.reductions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @author rferranti
 */
public class Every<T> implements Predicate<Iterator<T>> {

    private final Predicate<T> predicate;

    public Every(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot create Every with a null predicate");
        this.predicate = predicate;
    }

    @Override
    public boolean accept(Iterator<T> iterator) {
        dbc.precondition(iterator != null, "cannot call Every with a null iterator");
        while (iterator.hasNext()) {
            if (!predicate.accept(iterator.next())) {
                return false;
            }
        }
        return true;
    }
}
