package net.emaze.dysfunctional.filtering;

import net.emaze.dysfunctional.delegates.Predicate;

/**
 *
 * @author rferranti
 */
public class UntilCount<T> implements Predicate<T>{

    private long count = 0;
    private final long limit;

    public UntilCount(long limit) {
        this.limit = limit;
    }
    
    @Override
    public boolean test(T element) {
        return limit != count++;
    }

}
