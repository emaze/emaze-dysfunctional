package net.emaze.dysfunctional.ranges;

import java.util.function.Predicate;

/**
 *
 * @author rferranti
 */
public class RangeIsEmpty<R extends Range<T>, T> implements Predicate<R> {

    @Override
    public boolean test(R element) {
        return !element.iterator().hasNext();
    }
}
