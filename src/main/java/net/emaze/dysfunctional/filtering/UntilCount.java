package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 * A stateful predicate yielding true until counter hit the limit, false after
 * the limit is reached.
 *
 * @param <T> the parameter type
 * @author rferranti
 */
public class UntilCount<T> implements Predicate<T> {

    private long count = 0;
    private final long limit;

    public UntilCount(long limit) {
        this.limit = limit;
    }

    @Override
    public boolean accept(T element) {
        return limit > count++;
    }
}
