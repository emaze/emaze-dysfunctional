package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.dispatching.logic.Predicate;

/**
 *
 * @param <T>
 * @author rferranti
 */
public class UntilCount<T> implements Predicate<T>{

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
