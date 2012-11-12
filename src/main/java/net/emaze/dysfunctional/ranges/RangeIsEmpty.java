package net.emaze.dysfunctional.ranges;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @author rferranti
 */
public class RangeIsEmpty<R extends Range<T>, T> implements Predicate<R> {

    @Override
    public boolean accept(R element) {
        return !element.iterator().hasNext();
    }
}
